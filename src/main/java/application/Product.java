package application;

public abstract class Product {

	private int product_id;
	private String product_name;
	private double product_price; // euros
	private double product_size; // inches
	private double product_weight; // kg
	private String product_metal;
	private String category;
	private static int count = 0;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Product.count = count;
	}

	public Product(String product_name, double product_price, double product_size, double product_weight,
			String product_metal) {
		this.product_id = count++;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_size = product_size;
		this.product_weight = product_weight;
		this.product_metal = product_metal;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public double getProduct_size() {
		return product_size;
	}

	public void setProduct_size(double product_size) {
		this.product_size = product_size;
	}

	public double getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(double product_weight) {
		this.product_weight = product_weight;
	}

	public String getProduct_metal() {
		return product_metal;
	}

	public void setProduct_metal(String product_metal) {
		this.product_metal = product_metal;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
