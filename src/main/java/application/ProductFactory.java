package application;

public class ProductFactory {

	private Product product;

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
