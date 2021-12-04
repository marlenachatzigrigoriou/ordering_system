package application;

import java.util.ArrayList;

/**
 * Includes the information and data access functions about a supplier.
 * 
 * @author marlenachatzigrigoriou
 */
public abstract class Supplier {

	/**
	 * The auto-generated id of the supplier.
	 */
	private int supplier_id;

	/**
	 * The brand name of the supplier.
	 */
	private String supplier_name;

	/**
	 * The address of the supplier.
	 */
	private String supplier_address;

	/**
	 * A list that contains the products ids and corresponding prices that the
	 * supplier provides to our company.
	 */
	private ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply;

	/**
	 * A static counter that helps in the auto-generation of item_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class
	 * 
	 * @param supplier_name                        the supplier's name.
	 * @param supplier_address                     the supplier's address.
	 * @param productIdsAndPricesAvailableToSupply the supplier's products and
	 *                                             prices.
	 */
	public Supplier(String supplier_name, String supplier_address,
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply) {
		this.supplier_id = count++;
		this.supplier_name = supplier_name;
		this.supplier_address = supplier_address;
		this.productIdsAndPricesAvailableToSupply = productIdsAndPricesAvailableToSupply;
	}

	/**
	 * Getter function of the supplier id.
	 * 
	 * @return supplier's id
	 */
	public int getSupplier_id() {
		return supplier_id;
	}

	/**
	 * Setter function of the supplier id.
	 * 
	 * @param supplier_id the auto-generated id of the supplier.
	 */
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	/**
	 * Getter function of the supplier name.
	 * 
	 * @return supplier's name
	 */
	public String getSupplier_name() {
		return supplier_name;
	}

	/**
	 * Setter function of the supplier name.
	 * 
	 * @param supplier_name the name of the supplier.
	 */
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	/**
	 * Getter function of the supplier address.
	 * 
	 * @return supplier's address
	 */
	public String getSupplier_address() {
		return supplier_address;
	}

	/**
	 * Setter function of the supplier address.
	 * 
	 * @param supplier_address the address of the supplier.
	 */
	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	/**
	 * Getter function of the supplier provided products and prices.
	 * 
	 * @return supplier's provided products and prices
	 */
	public ArrayList<ArrayList<Double>> getProductIdsAndPricesAvailableToSupply() {
		return productIdsAndPricesAvailableToSupply;
	}

	/**
	 * Setter function of the supplier provided products and prices.
	 * 
	 * @param productIdsAndPricesAvailableToSupply the provided products and prices
	 *                                             of the supplier.
	 */
	public void setProductIdsAndPricesAvailableToSupply(
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply) {
		this.productIdsAndPricesAvailableToSupply = productIdsAndPricesAvailableToSupply;
	}

	/**
	 * Setter function of the counter. Only needed for the correct implementation of
	 * the unit tests.
	 * 
	 * @param count the counter that helps in the auto-generation of supplier_id.
	 */
	public static void setCount(int count) {
		Supplier.count = count;
	}

}