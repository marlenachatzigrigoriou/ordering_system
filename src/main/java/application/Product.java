package application;

/**
 * Includes the information and data access functions about a product.
 * 
 * @author marlenachatzigrigoriou
 */
public abstract class Product {

	/**
	 * The auto-generated id of the product.
	 */
	private int product_id;

	/**
	 * The name of the product.
	 */
	private String product_name;

	/**
	 * The price of the product in euros.
	 */
	private double product_price;

	/**
	 * The size of the product in inches.
	 */
	private double product_size;

	/**
	 * The weight of product in kilograms.
	 */
	private double product_weight;

	/**
	 * The metal of product.
	 */
	private String product_metal;

	/**
	 * The category of product.
	 */
	private String category;

	/**
	 * A static counter that helps in the auto-generation of product_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class.
	 * 
	 * @param product_name   the product's name.
	 * @param product_price  the product's price.
	 * @param product_size   the product's size.
	 * @param product_weight the product's weight.
	 * @param product_metal  the product's metal.
	 */
	public Product(String product_name, double product_price, double product_size, double product_weight,
			String product_metal) {
		this.product_id = count++;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_size = product_size;
		this.product_weight = product_weight;
		this.product_metal = product_metal;
	}

	/**
	 * Getter function of the product id.
	 * 
	 * @return product's id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * Setter function of the product id.
	 * 
	 * @param product_id the auto-generated id of the product.
	 */
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	/**
	 * Getter function of the product name.
	 * 
	 * @return product's name
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * Setter function of the product name.
	 * 
	 * @param product_name the name of the product.
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * Getter function of the product price.
	 * 
	 * @return product's price
	 */
	public double getProduct_price() {
		return product_price;
	}

	/**
	 * Setter function of the product price.
	 * 
	 * @param product_price the price of the product.
	 */
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	/**
	 * Getter function of the product size.
	 * 
	 * @return product's size
	 */
	public double getProduct_size() {
		return product_size;
	}

	/**
	 * Setter function of the product size.
	 * 
	 * @param product_size the size of the product.
	 */
	public void setProduct_size(double product_size) {
		this.product_size = product_size;
	}

	/**
	 * Getter function of the product weight.
	 * 
	 * @return product's weight
	 */
	public double getProduct_weight() {
		return product_weight;
	}

	/**
	 * Setter function of the product weight.
	 * 
	 * @param product_weight the weight of the product.
	 */
	public void setProduct_weight(double product_weight) {
		this.product_weight = product_weight;
	}

	/**
	 * Getter function of the product metal.
	 * 
	 * @return product's metal
	 */
	public String getProduct_metal() {
		return product_metal;
	}

	/**
	 * Setter function of the product metal.
	 * 
	 * @param product_metal the metal of the product.
	 */
	public void setProduct_metal(String product_metal) {
		this.product_metal = product_metal;
	}

	/**
	 * Getter function of the product category.
	 * 
	 * @return product's category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter function of the product category.
	 * 
	 * @param category the category of the product.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Setter function of the counter. Only needed for the correct
	 * implementation of the unit tests.
	 * 
	 * @param count the counter that helps in the auto-generation of oder_id.
	 */
	public static void setCount(int count) {
		Product.count = count;
	}

}
