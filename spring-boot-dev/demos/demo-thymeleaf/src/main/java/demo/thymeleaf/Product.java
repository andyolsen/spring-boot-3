package demo.thymeleaf;

public class Product {

	private int productID;
	private String description;
	private double price;

	public Product() {}
	
	public Product(String description, double price) {
		this.description = description;
		this.price = price;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s, £%.2f", productID, description, price);
	}
}
