package demo.thymeleaf;

import java.util.ArrayList;
import java.util.List;

public class Repository {

	// For simplicity, store data in static fields (to persist across multiple HTTP calls). In a real app, you'd probably use a database... 
	private static List<Product> products = new ArrayList<>();
	private static List<Customer> customers = new ArrayList<>(); 

	private static int nextProductID = 1;
	private static int nextCustomerID = 101;
	
	static {

		// Create some sample products and add to collection.
		Product product1 = new Product("Swansea City shirt", 45.00);	// :-)
		Product product2 = new Product("Bugatti Chiron", 1950000.99);
		Product product3 = new Product("Apple Mac Book Pro", 1999.99);
		Product product4 = new Product("Samsung UHD TV", 1999.99);
		Product product5 = new Product("The Good Book", 21.50);
		
		saveProduct(product1);
		saveProduct(product2);
		saveProduct(product3);
		saveProduct(product4);
		saveProduct(product5);
		
		// Create some sample customers and add to collection.
		Customer customer101 = new Customer("Matthew Smith", "m.smith@acme.com");
		Customer customer102 = new Customer("Mark Evans", "m.evans@acme.com");
		Customer customer103 = new Customer("Luke Solberg", "l.solberg@acme.com");
		Customer customer104 = new Customer("John Bird", "j.bird@acme.com");
		Customer customer105 = new Customer("Lydia Milsom", "l.milsom@acme.com");
		
		saveCustomer(customer101);
		saveCustomer(customer102);
		saveCustomer(customer103);
		saveCustomer(customer104);
		saveCustomer(customer105);

		// Customers buy some products.		
		customerBuysProduct(101, 1);
		customerBuysProduct(101, 2);

		customerBuysProduct(102, 1);
		customerBuysProduct(102, 2);
		customerBuysProduct(102, 5);
		
		customerBuysProduct(105, 5);
	}
	
	public static List<Product> getAllProducts() {
		return products;
	}

	public static List<Customer> getAllCustomers() {
		return customers;
	}

	public static Customer getCustomerByID(int id) {
		for (Customer c : customers){
			if (c.getCustomerID() == id){
				return c;
			}
		}
		return null;
	}

	public static Product getProductByID(int id) {
		for (Product p : products){
			if (p.getProductID() == id){
				return p;
			}
		}
		return null;
	}

	public static void saveCustomer(Customer newCustomer) {
		newCustomer.setCustomerID(nextCustomerID++);
		customers.add(newCustomer);
	}

	public static void saveProduct(Product newProduct) {
		newProduct.setProductID(nextProductID++);
		products.add(newProduct);
	}

	public static void customerBuysProduct(int customerID, int productID) {
		for (Customer c : customers){
			if (c.getCustomerID() == customerID){
				for (Product p : products){
					if (p.getProductID() == productID){
						c.addProduct(p);
						break;
					}
				}
				break;
			}
		}
	}
}
