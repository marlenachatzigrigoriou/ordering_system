package Facade;

import java.util.ArrayList;

import application.Supplier;
import applicationDAO.SupplierDAO;

public class FacadeSupplier {
    public void listSuppliers(ArrayList<Supplier> suppliersInTheSystem) {
    	SupplierDAO sudao = new SupplierDAO();
        for (Supplier supplier : suppliersInTheSystem){
        	sudao.viewSupplier(supplier);
        }
    }
}