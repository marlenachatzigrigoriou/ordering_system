package application;

/**
 * Pot class that inherits from the Product one.
 * 
 * @author marlenachatzigrigoriou
 */
public class Pot extends Product {

	/**
	 * Constructor class. Inherits from the Product constructor.
	 * 
	 * @param product_name   the product's name.
	 * @param product_price  the product's price.
	 * @param product_size   the product's size.
	 * @param product_weight the product's weight.
	 * @param product_metal  the product's metal.
	 */
	public Pot(String product_name, double product_price, double product_size, double product_weight,
			String product_metal) {
		super(product_name, product_price, product_size, product_weight, product_metal);
		super.setCategory("Pot");
	}

}
