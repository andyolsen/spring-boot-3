package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartServiceImpl implements CartService {

	@Autowired
	private Catalog catalog;

	@Autowired
	private CartRepository repository;

	@Override
	public void addItemToCart(int id, int quantity) {
		Item item = catalog.getItemById(id);
		if (item != null) {
			repository.add(id, quantity);
		}
	}
	
	@Override
	public void removeItemFromCart(int id) {
		repository.remove(id);
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
}