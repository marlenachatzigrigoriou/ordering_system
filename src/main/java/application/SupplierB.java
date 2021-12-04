package application;

import java.util.ArrayList;

/**
 * SupplierB class that inherits from the Supplier one.
 * 
 * @author marlenachatzigrigoriou
 */
public class SupplierB extends Supplier {

	/**
	 * Constructor class. Inherits from the Supplier constructor.
	 * 
	 * @param supplier_name                        the supplier's name.
	 * @param supplier_address                     the supplier's address.
	 * @param productIdsAndPricesAvailableToSupply the supplier's products and
	 *                                             prices.
	 */
	public SupplierB(String supplier_name, String supplier_address,
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply) {
		super(supplier_name, supplier_address, productIdsAndPricesAvailableToSupply);
	}

}
