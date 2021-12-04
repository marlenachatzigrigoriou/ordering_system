package facade;

import java.util.ArrayList;

import application.Supplier;
import applicationDAO.SupplierDAO;
/**
 * The Facade of te Supplier class.
 * 
 * @author marlenachatzigrigoriou
 */
public class FacadeSupplier {
    
	/**
	 * Prints the company's suppliers.
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 */
	public void listSuppliers(ArrayList<Supplier> suppliersInTheSystem) {
    	SupplierDAO sudao = new SupplierDAO();
        for (Supplier supplier : suppliersInTheSystem){
        	sudao.viewSupplier(supplier);
        }
    }
}