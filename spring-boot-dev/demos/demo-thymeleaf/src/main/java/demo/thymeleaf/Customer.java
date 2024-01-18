package demo.thymeleaf;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int customerID;
	private String name;
	private String emailAddress;
	private List<Product> products = new ArrayList<>();

	public Customer() {}
	
	public Customer(String name, String emailAddress) {
		this.name = name;
		this.emailAddress = emailAddress;
	}

	public Product getProduct(int productID) {
		for (Product p : products) {
			if (p.getProductID() == productID)
				return p;
		}
		return null;
	}

	public void addProduct(Product p) {
		this.products.add(p);
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int ownerID) {
		this.customerID = ownerID;
	}

	public List<Product> getProducts() {
		return new ArrayList<Product>(products);
	}

	public void setProducts(ArrayList<Product> animals) {
		this.products = animals;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString(){
		return String.format("[%d] %s, %s (%d product%s)", customerID, name, emailAddress, products.size(), products.size() == 1 ? "" : "s");
	}
}
