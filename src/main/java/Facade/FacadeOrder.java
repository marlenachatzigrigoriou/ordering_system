package Facade;

import java.util.ArrayList;
import java.util.Scanner;

import application.*;
import applicationDAO.*;

public class FacadeOrder {
	
	SalesmanDAO sdao = new SalesmanDAO();
	WarehouseDAO wdao = new WarehouseDAO();
	ManagerDAO mdao = new ManagerDAO();
	ShopDAO shdao = new ShopDAO();
	ProductDAO pdao = new ProductDAO();
	IncomingOrderDAO iodao = new IncomingOrderDAO();
	StockDAO stdao = new StockDAO();
	SupplierDAO sudao = new SupplierDAO();
	OutgoingOrderDAO oudao = new OutgoingOrderDAO();
    FacadeUser fu = new FacadeUser();
    FacadeShop fs = new FacadeShop();
    FacadeProduct fp = new FacadeProduct();
    FacadeStock fst = new FacadeStock();
    FacadeSupplier fsu = new FacadeSupplier();
    

	public void orderBySalesman(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Shop> shopsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem,
			ArrayList<Stock> stockOfProductsInTheSystem, Scanner scanner, User user_obj,
			ArrayList<Supplier> suppliersInTheSystem) {
		int shop_id = shdao.chooseShop(shopsInTheSystem, scanner);

		System.out.println("Choose the product(s) and the items for each:\n ");
		int[][] products_items = fp.addProductsInOrder(scanner, productsInTheSystem, user_obj, stockOfProductsInTheSystem,
				incomingOrdersInTheSystem, suppliersInTheSystem);

		System.out.print("Enter possible discount: ");
		double discount = iodao.enterDiscount(scanner);

		System.out.println("\nOrder Summary: ");
		IncomingOrderDAO.showIncomingOrderSummary(products_items, discount, shop_id, productsInTheSystem,
				shopsInTheSystem);
		if (approveFlow(scanner, "Submit", "Back")) {
			iodao.createNewIncomingOrder(discount, user_obj.getUser_id(), shop_id, products_items, productsInTheSystem,
					incomingOrdersInTheSystem, stockOfProductsInTheSystem);
			FacadeStock fst = new FacadeStock();
			fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
		}
	}

	public void orderByWarehouse(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem, Scanner scanner,
			User user_obj) {
		// these are the suppliers and the products they can supply you SYSOUT
		System.out.println("Table of products and their corresponding suppliers:");
		sudao.showSuppliersAndTheirProducts(productsInTheSystem, suppliersInTheSystem);
		System.out.println();
		System.out.println("Choose the supplier the order is for:\n ");
		fsu.listSuppliers(suppliersInTheSystem);
		System.out.println();
		int supplier_id = sudao.chooseSupplier(suppliersInTheSystem);
		// BASED ON SUPPLIER
		System.out.println("Choose the product(s) and the items for each:\n "); 
		int[][] products_items = fp.addProductsInOrder(scanner, productsInTheSystem, user_obj, null,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		System.out.println("\nOrder Summary: ");
		OutgoingOrderDAO.showOutgoingOrderSummary(products_items, supplier_id, productsInTheSystem,
				suppliersInTheSystem);
		if (approveFlow(scanner, "Submit", "Back")) {
			oudao.createNewOutgoingOrder(user_obj.getUser_id(), supplier_id, products_items, productsInTheSystem,
					outgoingOrdersInTheSystem, suppliersInTheSystem);
		}
		// we can make an order that will make our stock < 5 (even 0)
		// we just have to make an outgoing order, too, in this case!
	}

	public void editIncomingOrderSalesman(ArrayList<Order> incomingOrdersInTheSystem, User user_obj,
			ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			Scanner s, ArrayList<Supplier> suppliersInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {

		if (user_obj.getCategory().equals("Salesman") && !iodao.listOrdersFromSalemanUser(productsInTheSystem,
				usersInTheSystem, shopsInTheSystem, incomingOrdersInTheSystem, user_obj).isEmpty()) { // if it is
																										// pending and
																										// if it is made
																										// by him/she
			IncomingOrder io = incomingOrderToUpdate(s, incomingOrdersInTheSystem);

			if (io.getOrder_status().equals("Pending")) {

				// print order before edit
				System.out.println("This is the current order:");
				iodao.viewIncomingOrderInformation(io, productsInTheSystem, usersInTheSystem, shopsInTheSystem);
				System.out.println(
						"1. Add products\n2. Delete products\n3. Change items of existing products\n4. Change discount\n5. Delete order\n6. Done");
				int choice = chooseOption(s, 6, 1);
				String subba = "Back";

				while (choice != 6 && subba.equals("Back")) {
					if (choice == 1) {
						int[][] products_items = fp.addProductsInOrder(s, productsInTheSystem, user_obj,
								stockOfProductsInTheSystem, incomingOrdersInTheSystem, suppliersInTheSystem);
						iodao.updateIncomingOrderAddProducts(io, products_items, productsInTheSystem, shopsInTheSystem,
								s, stockOfProductsInTheSystem);
						fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
					} else if (choice == 2) {
						if (iodao.updateIncomingOrderDeleteProducts(io, productsInTheSystem, s, shopsInTheSystem,
								stockOfProductsInTheSystem)) {
							fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
							break;
						}

					} else if (choice == 3) {
						System.err.println("Not implemented yet.");
					} else if (choice == 4) {
						iodao.updateIncomingOrderDiscount(io, s, productsInTheSystem, shopsInTheSystem);
					} else if (choice == 5) {
						iodao.updateIncomingOrderDelete(io, s, stockOfProductsInTheSystem);
						break; // as he cannot edit the order anymore, the order is deleted
					}
					System.out.println(
							"1. Add products\n2. Delete products\n3. Change items of existing products\n4. Change discount\n5. Delete order\n6. Done");
					choice = chooseOption(s, 6, 1);
				}
			} else {
				System.out.println(
						"You cannot edit this order anymore because it has been proceeded by the warehouse staff.");
			}

		}
	}
	
	public int chooseOption(Scanner scanner, int upper_limit, int lower_limit) {
		boolean repeat = true;
		int choice = 0;
		while (repeat) {
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				if ((choice <= upper_limit && choice >= lower_limit) && choice == (int)choice) {
					repeat = false;
					return choice;
				} else {
					throw new Exception("You should enter only the number that you want to choose.");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return choice;
	}

	public void editIncomingOrderWM(ArrayList<Order> incomingOrdersInTheSystem, User user_obj,
			ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			Scanner s, ArrayList<Supplier> suppliersInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {

		if (user_obj.getCategory().equals("Warehouse") || user_obj.getCategory().equals("Manager")) {

			iodao.listAllIncomingOrderInformation(productsInTheSystem, usersInTheSystem, shopsInTheSystem);
			IncomingOrder io = incomingOrderToUpdate(s, incomingOrdersInTheSystem);

			if (io.getOrder_status().equals("Pending") || io.getOrder_status().equals("Packed")) { // he/she can only
																									// change an order
																									// to packed or
																									// shipped

				// print order before edit
				System.out.println("This is the current order:");
				iodao.viewIncomingOrderInformation(io, productsInTheSystem, usersInTheSystem, shopsInTheSystem);
				System.out.println(
						"\n1. Change status\n2. Add products\n3. Delete products\n4. Change items of existing products\n5. Change discount\n6. Delete order\n7. Done");
				int choice = chooseOption(s, 7, 1);
				String subba = "Back";

				while (choice != 7 && subba.equals("Back")) {

					if (choice == 1) {
						iodao.updateIncomingOrderStatus(io, s);
					} else if (choice == 2) {
						int[][] products_items = fp.addProductsInOrder(s, productsInTheSystem, user_obj,
								stockOfProductsInTheSystem, incomingOrdersInTheSystem, suppliersInTheSystem);
						iodao.updateIncomingOrderAddProducts(io, products_items, productsInTheSystem, shopsInTheSystem,
								s, stockOfProductsInTheSystem);
						fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
					} else if (choice == 3) {
						iodao.updateIncomingOrderDeleteProducts(io, productsInTheSystem, s, shopsInTheSystem,
								stockOfProductsInTheSystem);
						fst.listStock(stockOfProductsInTheSystem, productsInTheSystem);
					} else if (choice == 4) {
						System.err.println("Not implemented yet.");
					} else if (choice == 5) {
						iodao.updateIncomingOrderDiscount(io, s, productsInTheSystem, shopsInTheSystem);
					} else if (choice == 6) {
						iodao.updateIncomingOrderDelete(io, s, stockOfProductsInTheSystem);
					}
					System.out.println(
							"1. Change status\n2. Add products\n3. Delete products\n4. Change items of existing products\n5. Change discount\n6. Delete order\n7. Done");
					choice = chooseOption(s, 7, 1);
				}
			} else {
				System.out.println(
						"You cannot edit this order anymore because it is out of the warehouse management/authority.");
			}
		}
	}

	public IncomingOrder incomingOrderToUpdate(Scanner s, ArrayList<Order> incomingOrdersInTheSystem) {
		System.out.println("I want to edit the order: ");
		int order_id = chooseOrder(incomingOrdersInTheSystem, s);
		IncomingOrderDAO iodao = new IncomingOrderDAO();
		IncomingOrder io = (IncomingOrder) iodao.getOrderByOrderId(order_id, incomingOrdersInTheSystem);
		return io;
	}
	
	public int chooseOrder(ArrayList<Order> incomingOrdersInTheSystem, Scanner scanner) {
		ArrayList<Integer> orders_ids = new ArrayList<Integer>();
		for (Order o : incomingOrdersInTheSystem) {
			orders_ids.add(o.getOrder_id());
		}
		int order_id = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				order_id = scanner.nextInt();
				scanner.nextLine();
				if (orders_ids.contains(order_id)) {
					repeat = false;
				} else {
					throw new Exception("ERROR: you should enter an ID from the ones already on the screen.");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return order_id;
	}
	
	public boolean approveFlow(Scanner scanner, String choice1, String choice2) {
		String subba;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println(choice1 + "  |  " + choice2);
				subba = scanner.nextLine();
				if (subba.equals(choice1)) {
					repeat = false;
					return true;
				} else if (subba.equals(choice2)) {
					repeat = false;
					return false;
				} else {
					throw new Exception("ERROR: you should enter either \"" + choice1 + "\" or \"" + choice2 + "\"");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return false;
	}
	
    public void listAllOutgoingOrderInformation(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Supplier> suppliersInTheSystem ) { //order+product+items+shop+warehouse
		for (OutgoingOrder i : oudao.getAllOutgoingOrderInformationInTheSystem().keySet()){
			System.out.println("____   ____   ____   ____   ____   ____");            
			oudao.viewOutgoingOrderInformation(i, productsInTheSystem, usersInTheSystem, suppliersInTheSystem);
		}
	}
		
	public void listOrdersFromShop(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Shop> shopsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem, Scanner scanner) {
		IncomingOrderDAO iodao = new IncomingOrderDAO();
		iodao.listOrdersFromShop(productsInTheSystem, usersInTheSystem, shopsInTheSystem, incomingOrdersInTheSystem, scanner);
	}
    


}