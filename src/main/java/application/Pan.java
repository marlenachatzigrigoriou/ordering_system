package application;

public class Pan extends Product {

	public Pan(String product_name, double product_price, double product_size, double product_weight,
			String product_metal) {
		super(product_name, product_price, product_size, product_weight, product_metal);
		super.setCategory("Pan");
	}

}
