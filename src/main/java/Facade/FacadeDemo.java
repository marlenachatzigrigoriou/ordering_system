package Facade;

import java.util.ArrayList;
import java.util.Scanner;

import application.*;
import applicationDAO.*;

import applicationDAO.WarehouseDAO;


public class FacadeDemo {
	
    FacadeOrder fo = new FacadeOrder();
    FacadeUser fu = new FacadeUser();
    FacadeShop fs = new FacadeShop();
    FacadeProduct fp = new FacadeProduct();
    FacadeStock fst = new FacadeStock();
    FacadeSupplier fsu = new FacadeSupplier();
    
	public void salesmanInterface(User user_obj, Scanner scanner, ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> incomingOrdersInTheSystem, ArrayList<Supplier> suppliersInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {
		System.out.println(
				"1. View my orders\n2. Make an order\n3. Edit Order\n4. List shops\n5. List products\n6. Logout");
		int tab = fo.chooseOption(scanner, 6, 1);
		while (tab != 6) {
			if (tab == 1) {
				fu.listOrdersFromSalemanUser(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, user_obj);
			} else if (tab == 2) {
				fo.orderBySalesman(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, stockOfProductsInTheSystem, scanner, user_obj, suppliersInTheSystem);
			} else if (tab == 3) {
				fo.editIncomingOrderSalesman(incomingOrdersInTheSystem, user_obj, productsInTheSystem,
						usersInTheSystem, shopsInTheSystem, scanner, suppliersInTheSystem, stockOfProductsInTheSystem);
			} else if (tab == 4) {
				fs.listShops(shopsInTheSystem);
			} else if (tab == 5) {
				fp.listProducts(productsInTheSystem);
			}
			System.out.println(
					"1. View my orders\n2. Make an order\n3. Edit Order\n4. List shops\n5. List products\n6. Logout");
			tab = fo.chooseOption(scanner, 6, 1);
		}
	}
	
	public void warehouseInterface(User user_obj, Scanner scanner, ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Order> incomingOrdersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {
		System.out.println(
				"1. Make an order\n2. List shops\n3. List products\n4. View stock\n5. View my orders\n6. View suppliers\n"
						+ "7. View orders to suppliers\n8. View orders from shops\n9. Edit incoming"
						+ " order\n10. Edit outgoing order\n11. Logout");
		int tab = fo.chooseOption(scanner, 11, 1);
		while (tab != 11) {
			if (tab == 1) {
				fo.orderByWarehouse(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
						outgoingOrdersInTheSystem, scanner, user_obj);
			} else if (tab == 2) {
				fs.listShops(shopsInTheSystem);
			} else if (tab == 3) {
				fp.listProducts(productsInTheSystem);
			} else if (tab == 4) {
				fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
			} else if (tab == 5) {
				fu.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
						outgoingOrdersInTheSystem, user_obj);
			} else if (tab == 6) {
				fsu.listSuppliers(suppliersInTheSystem);
			} else if (tab == 7) {
				fo.listAllOutgoingOrderInformation(productsInTheSystem, usersInTheSystem, suppliersInTheSystem);
			} else if (tab == 8) {
				fo.listOrdersFromShop(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, scanner);
			} else if (tab == 9) {
				fo.editIncomingOrderWM(incomingOrdersInTheSystem, user_obj, productsInTheSystem, usersInTheSystem,
						shopsInTheSystem, scanner, suppliersInTheSystem, stockOfProductsInTheSystem);
			} else if (tab == 10) {
				System.err.println("Not implemented yet.");
			}
			System.out.println(
					"1. Make an order\n2. List shops\n3. List products\n4. View stock\n5. View my orders\n6. View suppliers\n"
							+ "7. View orders to suppliers\n8. View orders from shops\n9. Edit incoming"
							+ " order\n10. Edit outgoing order\n11. Logout");
			tab = fo.chooseOption(scanner, 11, 1);
		}
	}
	
	public void managerInterface(User user_obj, Scanner scanner, ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Order> incomingOrdersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {

		System.out.println(
				"1. Make an incoming order\n2. Make an outcoming order\n3. List shops\n4. List products\n5. View stock\n6. View my orders\n7. View suppliers\n"
						+ "8. View orders to suppliers\n9. View orders from shops\n10. Edit incoming"
						+ " order\n11. Edit outgoing order\n12. Create user\n13. Logout");
		int tab = fo.chooseOption(scanner, 13, 1);

		while (tab != 13) {
			if (tab == 1) {
				fo.orderByWarehouse(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
						outgoingOrdersInTheSystem, scanner, user_obj);
			} else if (tab == 2) {
				fo.orderBySalesman(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, stockOfProductsInTheSystem, scanner, user_obj, suppliersInTheSystem);
			} else if (tab == 3) {
				fs.listShops(shopsInTheSystem);
			} else if (tab == 4) {
				fp.listProducts(productsInTheSystem);
			} else if (tab == 5) {
				fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
			} else if (tab == 6) {
				System.out.println("Incoming orders:");
				fu.listOrdersFromSalemanUser(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, user_obj);
				System.out.println("Outgoing orders:");
				fu.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
						outgoingOrdersInTheSystem, user_obj);
			} else if (tab == 7) {
				fsu.listSuppliers(suppliersInTheSystem);
			} else if (tab == 8) {
				fo.listAllOutgoingOrderInformation(productsInTheSystem, usersInTheSystem, suppliersInTheSystem);
			} else if (tab == 9) {
				fo.listOrdersFromShop(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
						incomingOrdersInTheSystem, scanner);
			} else if (tab == 10) {
				fo.editIncomingOrderWM(incomingOrdersInTheSystem, user_obj, productsInTheSystem, usersInTheSystem,
						shopsInTheSystem, scanner, suppliersInTheSystem, stockOfProductsInTheSystem);
			} else if (tab == 11) {
				System.err.println("Not implemented yet.");
			} else if (tab == 12) {
				usersInTheSystem = fu.createNewUser(scanner, usersInTheSystem);
			}
			System.out.println(
					"1. Make an incoming order\n2. Make an outcoming order\n3. List shops\n4. List products\n5. View stock\n6. View my orders\n7. View suppliers\n"
							+ "8. View orders to suppliers\n9. View orders from shops\n10. Edit incoming"
							+ " order\n11. Edit outgoing order\n12. Create user\n13. Logout");
			tab = fo.chooseOption(scanner, 13, 1);

		}
	}
	
	public void printGeneratedData(ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> incomingOrdersInTheSystem, ArrayList<Product> productsInTheSystem,
			ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Supplier> suppliersInTheSystem,
			ArrayList<Order> outgoingOrdersInTheSystem) {
		
		SalesmanDAO sdao = new SalesmanDAO();
		WarehouseDAO wdao = new WarehouseDAO();
		ManagerDAO mdao = new ManagerDAO();
	    IncomingOrderDAO iodao = new IncomingOrderDAO();
	    OutgoingOrderDAO oudao = new OutgoingOrderDAO();
	    
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("Users: \n");
		sdao.listUsers(usersInTheSystem);
		wdao.listUsers(usersInTheSystem);
		mdao.listUsers(usersInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("Shops: \n");
		fs.listShops(shopsInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("Products: \n");
		fp.listProducts(productsInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("All incoming order objects:\n");
		iodao.listOrders(incomingOrdersInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("All incoming orders in detail:\n");
		iodao.listAllIncomingOrderInformation(productsInTheSystem, usersInTheSystem, shopsInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("Stock:\n");
		fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("Suppliers: \n");
		fsu.listSuppliers(suppliersInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("All outgoing order objects:\n");
		oudao.listOrders(outgoingOrdersInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
		System.out.println("All outgoing orders in detail:\n");
		fo.listAllOutgoingOrderInformation(productsInTheSystem, usersInTheSystem, suppliersInTheSystem);
		System.out.println(
				"_____________________________________________________________________________________________________");
	}

}