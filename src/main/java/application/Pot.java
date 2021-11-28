package application;

public class Pot extends Product {

	public Pot(String product_name, double product_price, double product_size, double product_weight,
			String product_metal) {
		super(product_name, product_price, product_size, product_weight, product_metal);
		super.setCategory("Pot");
	}

}
