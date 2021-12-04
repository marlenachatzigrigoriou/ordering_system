package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Product;
import application.Supplier;

public class SupplierDAO {

	/**
	 * Stores in the memory the given supplier.
	 * 
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @param s                    Supplier object to be stored
	 */
	public void storeSuppliersInMemory(ArrayList<Supplier> suppliersInTheSystem, Supplier s) {
		suppliersInTheSystem.add(s);
	}

	/**
	 * Prints supplier's name and address.
	 * 
	 * @param supplier Supplier object
	 */
	public void viewSupplier(Supplier supplier) {
		System.out.println("ID: " + supplier.getSupplier_id() + " | Name: " + supplier.getSupplier_name()
				+ ", Address: " + supplier.getSupplier_address());
	}

	/**
	 * Returns the supplier object to which corresponds the given supplier id.
	 * 
	 * @param supplier_id          the supplier id
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @return the Supplier object to which corresponds the id
	 */
	public Supplier getSupplierById(int supplier_id, ArrayList<Supplier> suppliersInTheSystem) {
		for (Supplier supplier : suppliersInTheSystem) {
			if (supplier.getSupplier_id() == supplier_id) {
				return supplier;
			}
		}
		return null;
	}

	/**
	 * Returns the price of the product that the suppliers cost.
	 * 
	 * @param product_id           the id of the given product
	 * @param supplier_id          the supplier id
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @return product's price
	 */
	public double getPriceOfPoduct(int product_id, ArrayList<Supplier> suppliersInTheSystem, int supplier_id) {

		Supplier supplier = getSupplierById(supplier_id, suppliersInTheSystem);
		ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply = supplier
				.getProductIdsAndPricesAvailableToSupply();
		double price = 0.0;
		for (ArrayList<Double> ad : productIdsAndPricesAvailableToSupply) {
			if (ad.get(0).intValue() == product_id) {
				price = ad.get(1);
				return price;
			}
		}
		return price;
	}

	/**
	 * Returns the supplier id that provides to the company the given product.
	 * 
	 * @param product_id           the id of the given product
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @return the supplier id of the given product
	 */
	public int getSupplierIdOfProduct(int product_id, ArrayList<Supplier> suppliersInTheSystem) {
		for (Supplier s : suppliersInTheSystem) {
			ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply = s
					.getProductIdsAndPricesAvailableToSupply();
			for (ArrayList<Double> ad : productIdsAndPricesAvailableToSupply) {
				if (ad.get(0).intValue() == product_id) {
					return s.getSupplier_id();
				}
			}
		}
		return 10000000;
	}

	/**
	 * Prints the products that each supplier provides.
	 * 
	 * @param productsInTheSystem  the stored in the memory products
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 */
	public void showSuppliersAndTheirProducts(ArrayList<Product> productsInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem) {
		System.out.println("ID |   Product   | Supplier | Price ");
		for (Supplier s : suppliersInTheSystem) {
			for (ArrayList<Double> ar : s.getProductIdsAndPricesAvailableToSupply()) {
				for (Product p : productsInTheSystem) {
					if (ar.get(0).intValue() == p.getProduct_id()) {
						System.out.println(p.getProduct_id() + " | " + p.getProduct_name() + " | "
								+ s.getSupplier_name() + " | " + ar.get(1) + "€");
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Checks the validity of supplier's id input. 
	 * @param suppliers_ids valid suppliers
	 * @param supplier_id given supplier
	 * @return if the input was valid
	 */
	public boolean validSupplier(ArrayList<Integer> suppliers_ids, int supplier_id) {
	      if (!suppliers_ids.contains(supplier_id)) {
	         System.err.println("ERROR: you should enter an ID from the ones already on the screen.");
	         return false;
	      }
	     
	      return true;
	}
	
	/**
	 * Choose supplier.
	 * 
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @return the chosen supplier
	 */
	public int chooseSupplier(ArrayList<Supplier> suppliersInTheSystem) {
	      Scanner scanner = new Scanner(System.in);
	      ArrayList<Integer> suppliers_ids = new ArrayList<Integer>();
	      for (Supplier s : suppliersInTheSystem) {
	         suppliers_ids.add(s.getSupplier_id());
	      }
	      int supplier_id = 0;

	      do {
	         String supplier_string = scanner.nextLine();
	         supplier_id = Integer.parseInt(supplier_string);   
	      } while(!validSupplier(suppliers_ids, supplier_id));
	     
	      scanner.close();
	      return supplier_id;
	}

}
