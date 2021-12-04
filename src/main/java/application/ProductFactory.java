package application;

/**
 * The factory of Product class, that generates Product objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class ProductFactory {

	/**
	 * Object of class Product.
	 */
	private Product product;

	/**
	 * Generates Product objects, depending on the defined product category.
	 * 
	 * @param product_name   the product's name.
	 * @param product_price  the product's price.
	 * @param product_size   the product's size.
	 * @param product_weight the product's weight.
	 * @param product_metal  the product's metal.
	 * @param category       the product's category.
	 * @return Product object.
	 */
	public Product createProduct(String product_name, double product_price, double product_size, double product_weight,
			String product_metal, String category) {
		if (category.equals("Pan")) {
			product = new Pan(product_name, product_price, product_size, product_weight, product_metal);
		} else if (category.equals("Pot")) {
			product = new Pot(product_name, product_price, product_size, product_weight, product_metal);
		}
		return product;
	}

}
