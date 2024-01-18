package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartServiceImpl implements CartService {

	@Autowired
	private Catalog catalog;

	@Autowired
	private CartRepository repository;

	@Autowired
	@Qualifier("verboseTranscript")
	private Transcript recentActivity;

	@Value("${contactEmail}")
	private String contactEmail;

	@Value("${onlineRetailer.salesTaxRate}")
	private double salesTaxRate;

	@Value("${onlineRetailer.deliveryCharge.normal}")
	private double standardDeliveryCharge;

	@Value("${onlineRetailer.deliveryCharge.threshold}")
	private double deliveryChargeThreshold;

	@Override
	public void addItemToCart(int id, int quantity) {
		Item item = catalog.getItemById(id);
		if (item != null) {
			repository.add(id, quantity);
			recentActivity.log(String.format("Added item to cart, id: %d, quantity: %d", id, quantity));
		}
	}
	
	@Override
	public void removeItemFromCart(int id) {
		repository.remove(id);
		recentActivity.log(String.format("Removed item from cart, id: %d", id));
	}
	
	@Override
	public Map<Integer, Integer> getAllItemsInCart() {
		return repository.getAll();
	}
	
	@Override
	public double calculateCartCost() {
		Map<Integer, Integer> items = repository.getAll();
		
		double totalCost = 0;
		for (var entry: items.entrySet()) {
			int id = entry.getKey();
			int quantity = entry.getValue();
			double itemCost = catalog.getItemById(id).getPrice() * quantity;
			totalCost += itemCost;
		}
		return totalCost;
	}

	@Override
	public void displayRecentActivity() {
		recentActivity.display();
	}

	@Override
	public double calculateSalesTax() {
		return salesTaxRate * calculateCartCost();
	}

	@Override
	public double calculateDeliveryCharge() {
		double totalCost = calculateCartCost();
		if (totalCost == 0 || totalCost >= deliveryChargeThreshold) {
			return 0;
		}
		else {
			return standardDeliveryCharge;
		}
	}

}