package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CartRepositoryImpl implements CartRepository {

	// What's in the shopping cart (the key is an item id, the value is the quantity for that item).
	private Map<Integer, Integer> cart = new HashMap<>();

	@Override
	public void add(int itemId, int quantity) {
		Integer existingQuantity = cart.get(itemId);
		if (existingQuantity != null) {
			quantity += existingQuantity;			
		}
		cart.put(itemId,  quantity);
	}
	
	@Override
	public void remove(int itemId) {
		cart.remove(itemId);
	}
	
	@Override
	public Map<Integer, Integer> getAll() {
		return cart;
	}
}
