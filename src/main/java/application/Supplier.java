package application;

import java.util.ArrayList;

public abstract class Supplier {

	private int supplier_id;
	private String supplier_name;
	private String supplier_address;
	private ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply;
	private static int count = 0;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Supplier.count = count;
	}

	public Supplier(String supplier_name, String supplier_address,
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply) {
		this.supplier_id = count++;
		this.supplier_name = supplier_name;
		this.supplier_address = supplier_address;
		this.productIdsAndPricesAvailableToSupply = productIdsAndPricesAvailableToSupply;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public ArrayList<ArrayList<Double>> getProductIdsAndPricesAvailableToSupply() {
		return productIdsAndPricesAvailableToSupply;
	}

	public void setProductIdsAndPricesAvailableToSupply(
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply) {
		this.productIdsAndPricesAvailableToSupply = productIdsAndPricesAvailableToSupply;
	}

}