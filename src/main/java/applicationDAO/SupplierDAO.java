package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Product;
import application.Supplier;

public class SupplierDAO{
	
    public void storeSuppliersInMemory(ArrayList<Supplier> suppliersInTheSystem, Supplier s){
    	suppliersInTheSystem.add(s);
    }
    

    public void viewSupplier(Supplier supplier){
        System.out.println("ID: " + supplier.getSupplier_id() + " | Name: " + supplier.getSupplier_name() + ", Address: " + supplier.getSupplier_address());
    }

    public Supplier getSupplierById(int supplier_id, ArrayList<Supplier> suppliersInTheSystem){
        for (Supplier supplier : suppliersInTheSystem){
            if ( supplier.getSupplier_id() == supplier_id){
                return supplier;
            }
        }
        return null;
    }
    
    public double getPriceOfPoduct(int product_id, ArrayList<Supplier> suppliersInTheSystem, int supplier_id) {
    	
		Supplier supplier = getSupplierById(supplier_id, suppliersInTheSystem);
		ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply = supplier.getProductIdsAndPricesAvailableToSupply();
//		System.out.println("hjk" + productIdsAndPricesAvailableToSupply);
		double price = 0.0;
		for (ArrayList<Double> ad : productIdsAndPricesAvailableToSupply) {
			if (ad.get(0).intValue() == product_id) { 
				price = ad.get(1);
				return price;
			}
		}
		return price;
    }
    
    public int getSupplierIdOfProduct(int product_id, ArrayList<Supplier> suppliersInTheSystem) {
    	for (Supplier s : suppliersInTheSystem) {
    		ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply = s.getProductIdsAndPricesAvailableToSupply();
    		for (ArrayList<Double> ad : productIdsAndPricesAvailableToSupply) {
    			if (ad.get(0).intValue() == product_id) { 
    				return s.getSupplier_id();
    			}
    		}
    	}
    	return 10000000;
	}
    
    public void showSuppliersAndTheirProducts(ArrayList<Product> productsInTheSystem, ArrayList<Supplier> suppliersInTheSystem) {
		System.out.println(  "ID |   Product   | Supplier | Price ");
    	for (Supplier s : suppliersInTheSystem) {
    		for (ArrayList<Double> ar : s.getProductIdsAndPricesAvailableToSupply()) {
    	    	for (Product p : productsInTheSystem) {
	        		if (ar.get(0).intValue() == p.getProduct_id()) {
	            		System.out.println(  p.getProduct_id() + " | " + p.getProduct_name() + " | " + s.getSupplier_name() + " | " + ar.get(1)+ "€");
	            		break;
	        		}
    	    	}
    		}
    	}
    }
    
	public int chooseSupplier(ArrayList<Supplier> suppliersInTheSystem) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> suppliers_ids = new ArrayList<Integer>();
		for (Supplier s : suppliersInTheSystem) {
			suppliers_ids.add(s.getSupplier_id());
		}
		int supplier_id = 0;
		boolean repeat = true;
		while (repeat) {
			try {
//				supplier_id = scanner.nextInt();
//				scanner.nextLine();
				String supplier_string = scanner.nextLine();
				supplier_id = Integer.parseInt(supplier_string);
				if (suppliers_ids.contains(supplier_id)) {
					repeat = false;
				} else {
					throw new Exception("ERROR: you should enter an ID from the ones already on the screen.");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		scanner.close();
		return supplier_id;
	}
	
//	public void p(int supplier_id, ) {
//		Scanner scanner = new Scanner(System.in);
//		try {
////			supplier_id = scanner.nextInt();
////			scanner.nextLine();
//			String supplier_string = scanner.nextLine();
//			supplier_id = Integer.parseInt(supplier_string);
//			if (suppliers_ids.contains(supplier_id)) {
//				repeat = false;
//			} else {
//				throw new Exception("ERROR: you should enter an ID from the ones already on the screen.");
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//			repeat = true;
//		}
//	}

    
}
