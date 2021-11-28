package applicationDAO;

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Facade.FacadeOrder;
import application.Product;
import application.Shop;
import application.Stock;
import application.User;
import application.Order;
import application.OrderFactory;
import application.IncomingOrder;


/***
 * 
 * @author marlenachatzigrigoriou  The purpose of this class is to find the 
 *                  minimum moves that a knight has to do from his source position to his 
 *                  destination. The algorithm implements Breadth First Search in a Generic Tree.
 * @since November 2021
 */
public class IncomingOrderDAO extends OrderDAO {


    // one HashMap that contains in each order_id = key --> all the required ids to define a whole order
    // i have it as static --> that is because i want the list to keep the data of the class it is in. if i take it off, the ddao.listAllIncomingOrderInformation 
    // in the demo.java will "restart" the map and it will not contain data, so, the listAllIncomingOrderInformation will print nothing
	// (incoming_order, [[salesman_id], [shop_id], [products_ids], [items_ids]])
	
    /*
    * The root node of the generic tree
    */
	private static HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>> allIncomingOrderInformationInTheSystem = new HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>>();

    /***
    * Checks if the knight is able to move to 
    * coordinates (x,y) of the chessboard.
    * A knight cannot go out of the chessboard.
    * 
    * @author marlenachatzigrigoriou
    * @param x coordinate x
    * @param y coordinate y
    * @param board_width chessbord width
    * @param board_height chessbord height
    */
	public HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>> getAllIncomingOrderInformationInTheSystem() {
		return allIncomingOrderInformationInTheSystem;
	}

	
//	@Override
//	public void viewOrder(Order order) {
//		IncomingOrder ino = (IncomingOrder) order;
//		System.out.println("ID:  " + ino.getOrder_id() + " | Status: " + ino.getOrder_status() + ", Date: " + ino.getOrder_date()
//				+ ", Total_cost: " + ino.getTotal_cost() + "€ (discount factored in total cost: "
//				+ ino.getOrder_discount() + ")");
//	}

	@Override
	public ArrayList<Order> storeOrdersInMemory(ArrayList<Order> incomingOrdersInTheSystem, Order o) {
		incomingOrdersInTheSystem.add(o);
		return incomingOrdersInTheSystem;
	}


	@Override
	public ArrayList<Integer> getOrdersByUserId(int user_id) {
		ArrayList<Integer> orders_ids = new ArrayList<Integer>();
		for (IncomingOrder order : allIncomingOrderInformationInTheSystem.keySet()) {
			int userid = allIncomingOrderInformationInTheSystem.get(order).get(0).get(0);
			if (user_id == userid) {
				orders_ids.add(order.getOrder_id());
			}
		}
		return orders_ids;
	}

	@Override
	public void storeAllOrderInformationInMemory(Order order, ArrayList<Integer> salesman_id,
			ArrayList<Integer> shop_id, ArrayList<Integer> products_ids, ArrayList<Integer> items_ids) {
		ArrayList<ArrayList<Integer>> order_info = new ArrayList<ArrayList<Integer>>();
		order_info.add(salesman_id);
		order_info.add(shop_id);
		order_info.add(products_ids);
		order_info.add(items_ids);
		allIncomingOrderInformationInTheSystem.put((IncomingOrder) order, order_info);
	}

	public void listAllIncomingOrderInformation(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem) { // order+product+items+shop+salesman
		for (IncomingOrder i : getAllIncomingOrderInformationInTheSystem().keySet()) {
			viewIncomingOrderInformation(i, productsInTheSystem, usersInTheSystem, shopsInTheSystem);
		}
	}

	public void printProductsOfIncomingOrder(ArrayList<Product> productsInTheSystem, IncomingOrder i) {
		ArrayList<Integer> products_ids = getAllIncomingOrderInformationInTheSystem().get(i).get(2);
		ArrayList<Integer> items_quantity = getAllIncomingOrderInformationInTheSystem().get(i).get(3);
		ProductDAO pdao = new ProductDAO();
		for (int pi = 0; pi < products_ids.size(); pi++) {
			if (items_quantity.get(pi) != 0) {
				System.out.println(
						"    " + pdao.getProductById(products_ids.get(pi), productsInTheSystem).getProduct_name()
								+ "           " + items_quantity.get(pi) + "           "
								+ pdao.getProductById(products_ids.get(pi), productsInTheSystem).getProduct_price()
										* items_quantity.get(pi));
			}
		}
	}
    
    
	public void viewIncomingOrderInformation(IncomingOrder i, ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem) {
		System.out.println("____   ____   ____   ____   ____   ____");
		viewOrder(i);
		System.out.println("     Products           Items           Cost");
		printProductsOfIncomingOrder(productsInTheSystem, i);
		ShopDAO shdao = new ShopDAO();
		SalesmanDAO sdao = new SalesmanDAO();
		System.out.print("Responsible salesman: ");
		int salesman_id = getAllIncomingOrderInformationInTheSystem().get(i).get(0).get(0);
		sdao.viewUser(sdao.getUserByUserId(salesman_id, usersInTheSystem));
		System.out.print("Purchase from: ");
		int shop_id = getAllIncomingOrderInformationInTheSystem().get(i).get(1).get(0);
		shdao.viewShop(shdao.getShopById(shop_id, shopsInTheSystem));
		
	}

	public static void showIncomingOrderSummary(int[][] products_items, double discount, int shop_id,
			ArrayList<Product> productsInTheSystem, ArrayList<Shop> shopsInTheSystem) {

		ProductDAO pdao = new ProductDAO();
		double total_cost = 0.0;
		System.out.println("     Products           Items");
		for (int pi = 0; pi < products_items.length; pi++) {
			if (products_items[pi][1] != 0) {
				System.out.println(
						"    " + pdao.getProductById(products_items[pi][0], productsInTheSystem).getProduct_name()
								+ "         " + products_items[pi][1]);
				total_cost = total_cost
						+ pdao.getProductById(products_items[pi][0], productsInTheSystem).getProduct_price()
								* products_items[pi][1];
			}
		}
		total_cost = total_cost - total_cost * discount;
		System.out.println("Total cost: " + total_cost + "(discount = " + discount + ")");
		System.out.print("Purchase from: ");
		ShopDAO shdao = new ShopDAO();
		shdao.viewShop(shdao.getShopById(shop_id, shopsInTheSystem));
		System.out.println();
	}
    
    


	public void listOrdersFromShop(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Shop> shopsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem, Scanner scanner) {
		ShopDAO sdao = new ShopDAO();
		int shop_id = sdao.chooseShop(shopsInTheSystem, scanner);
		ArrayList<Integer> orders_ids = getOrdersByShopId(shop_id);
		if (!orders_ids.isEmpty()) {
			for (int i : orders_ids) {
				Order order = getOrderByOrderId(i, incomingOrdersInTheSystem);
				viewIncomingOrderInformation((IncomingOrder) order, productsInTheSystem, usersInTheSystem,
						shopsInTheSystem);
			}
		} else {
			System.out.println("No orders from this shop yet.");
		}
	}
    
    
	public ArrayList<Integer> listOrdersFromSalemanUser(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> incomingOrdersInTheSystem, User user_obj) {
		int salesman_id = user_obj.getUser_id();
		String salesman_name = user_obj.getFull_name();
		System.out.println("Orders submitted by me: " + salesman_name + " | ID: " + salesman_id);
		ArrayList<Integer> orders_ids = getOrdersByUserId(salesman_id);
		if (orders_ids.size() == 0) {
			System.out.println("None yet");
		} else {
			for (int i : orders_ids) {
				Order order = getOrderByOrderId(i, incomingOrdersInTheSystem);
				viewIncomingOrderInformation((IncomingOrder) order, productsInTheSystem, usersInTheSystem,
						shopsInTheSystem);
			}
		}
		return orders_ids;
	}

	// whatever was usergenerator for its factory in generator class
	public IncomingOrder submitIncomingOrder(double order_discount, int[][] products_items,
			ArrayList<Product> productsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem) {
		double total_cost = calculateIncomingOrderTotalCost(products_items, productsInTheSystem);
		Date date = Calendar.getInstance().getTime();
//        String status = "Pending"; // because we just created the order
		OrderFactory ofac = new OrderFactory();
		IncomingOrder ino = (IncomingOrder) ofac.createOrder(total_cost, date, order_discount, "Incoming");
//        IncomingOrder ino = new IncomingOrder(total_cost, date, order_discount); //the final total_cost (calculated with the discount) is generated here!
		storeOrdersInMemory(incomingOrdersInTheSystem, ino);
		return ino;
	}


	// submit-store-reduce
	public Order createNewIncomingOrder(double order_discount, int salesman_id, int shop_id, int[][] products_items,
			ArrayList<Product> productsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem,
			ArrayList<Stock> stockOfProductsInTheSystem) {
		IncomingOrder order = submitIncomingOrder(order_discount, products_items, productsInTheSystem,
				incomingOrdersInTheSystem);

		ArrayList<Integer> products_ids = new ArrayList<>(), items_ids = new ArrayList<>(),
				salesman = new ArrayList<>(), shop = new ArrayList<>();
		for (int[] k : products_items) {
			products_ids.add(k[0]);
			items_ids.add(k[1]);
		}
		salesman.add(salesman_id);
		shop.add(shop_id);
		StockDAO stdao = new StockDAO(); // [p1, p2, p3] [i1, i2, i3]
		storeAllOrderInformationInMemory(order, salesman, shop, products_ids, items_ids);
		stdao.reduceStock(stockOfProductsInTheSystem, products_items);
		return order;
	}

	public ArrayList<Integer> getOrdersByShopId(int shop_id) {
		ArrayList<Integer> orders_ids = new ArrayList<Integer>();
		for (IncomingOrder order : allIncomingOrderInformationInTheSystem.keySet()) {
			int shopid = allIncomingOrderInformationInTheSystem.get(order).get(1).get(0);
			if (shop_id == shopid) {
				orders_ids.add(order.getOrder_id());
			}
		}
		return orders_ids;
	}
    

	// total cost before discount
	public double calculateIncomingOrderTotalCost(int products_items[][], ArrayList<Product> productsInTheSystem) { // before
																													// discount
		// products_items = N x 2
		//
		// product_id   |   item_quantity
		// _____________________________
		// |     1             5          --> product+item
		// |
		// |     2            10          --> product+item
		//
		// ex.: products_items[0,0] & products_items[1,0] --> buy 20 items of product 1
		// --> 20 * (price of 1) = cost
		double total_cost = 0.00;
		ProductDAO pdao = new ProductDAO();
		for (int i = 0; i < products_items.length; i++) { // for all the selected (by the salesman) products of that
															// specific order
			int product_id = products_items[i][0];
			Product temp_product = pdao.getProductById(product_id, productsInTheSystem);
			total_cost += temp_product.getProduct_price() * products_items[i][1];
		}
		return total_cost;
	}

    


	public boolean submitUpdate(int[][] products_items, ArrayList<Product> productsInTheSystem,
			ArrayList<Shop> shopsInTheSystem, IncomingOrder order, Scanner s, double discount) {
		boolean delete_order = false;
		for (int it[] : products_items) {
			if (it[1] != 0) {
				delete_order = false;
			} else {
				delete_order = true;
			}
		} // in case you choose "2" in edit and you delete all the products of that order
		if (delete_order) {
			System.out.println("Your order is going to be deleted");
		} else {
			int shop_id = getAllIncomingOrderInformationInTheSystem().get(order).get(1).get(0);
			System.out.println("Your updated order: ");
			showIncomingOrderSummary(products_items, discount, shop_id, productsInTheSystem, shopsInTheSystem);
		}
		FacadeOrder fo = new FacadeOrder();
		return fo.approveFlow(s, "Submit", "Back");
	}
    
    
	// salesman cannot change shop_id, salesman_id, status, date. only --> products,
	// items, discount
	public void updateIncomingOrderAddProducts(IncomingOrder order, int new_products_items[][],
			ArrayList<Product> productsInTheSystem, ArrayList<Shop> shopsInTheSystem, Scanner s,
			ArrayList<Stock> stockOfProductsInTheSystem) {
		// joint new with existing products-items
		ArrayList<Integer> products = getAllIncomingOrderInformationInTheSystem().get(order).get(2);
		ArrayList<Integer> items = getAllIncomingOrderInformationInTheSystem().get(order).get(3);
		for (int i = 0; i < new_products_items.length; i++) {
			products.add(new_products_items[i][0]);
			items.add(new_products_items[i][1]);
		}

		int products_items[][] = convertArrayListInto2DTable(products, items);

		// total cost before discount
		double total_cost = calculateIncomingOrderTotalCost(products_items, productsInTheSystem);
		// discount
		double discount = order.getOrder_discount();

		if (submitUpdate(products_items, productsInTheSystem, shopsInTheSystem, order, s, discount)) {
			// update order
			getAllIncomingOrderInformationInTheSystem().get(order).set(2, products);
			getAllIncomingOrderInformationInTheSystem().get(order).set(3, items);
			order.setTotal_cost(total_cost - total_cost * discount);
			// update stock
			StockDAO stdao = new StockDAO();
			stdao.reduceStock(stockOfProductsInTheSystem, new_products_items);
		} else {
			System.out.println("Order was not submitted!");
		}
	}


	public ArrayList<Integer> chooseProductsToDelete(IncomingOrder order, ArrayList<Product> productsInTheSystem,
			Scanner scanner) {
		System.out.println("Enter the ID of products you want to delete (one each time):");
		ProductDAO pdao = new ProductDAO();
		ArrayList<Product> productsInOrder = pdao.listProductsOfOrder(order, productsInTheSystem);
		int no_more_products_in_order = productsInOrder.size();
		boolean next_add = true;
		ArrayList<Integer> products = new ArrayList<Integer>(); // products to delete
		FacadeOrder fo = new FacadeOrder();
		while (next_add) {
			products.add(pdao.chooseProduct(productsInOrder, scanner));
			if (--no_more_products_in_order == 0) {
				break;
			}
			next_add = fo.approveFlow(scanner, "Add product ID to delete", "Next");
		}
		return products;
	}

	public int[][] getDeletedFromOrderProducts(ArrayList<Integer> products, IncomingOrder order) {
		int deletedFromOrderProducts[][] = new int[products.size()][2]; // i want to save them temporarily (the products
																		// but now and their items) so i can increase
																		// stock
		for (int i = 0; i < products.size(); i++) {
			deletedFromOrderProducts[i][0] = products.get(i);
			int item_index = getAllIncomingOrderInformationInTheSystem().get(order).get(2)
					.indexOf(deletedFromOrderProducts[i][0]);
			deletedFromOrderProducts[i][1] = getAllIncomingOrderInformationInTheSystem().get(order).get(3)
					.get(item_index);
		}
		return deletedFromOrderProducts;
	}

	public int[][] getUpdatedProductsItems(ArrayList<Integer> products, IncomingOrder order) {
		int updated_products_items[][] = new int[getAllIncomingOrderInformationInTheSystem().get(order).get(2)
				.size()][2];
		for (int i = 0; i < getAllIncomingOrderInformationInTheSystem().get(order).get(2).size(); i++) {
			updated_products_items[i][0] = getAllIncomingOrderInformationInTheSystem().get(order).get(2).get(i);
			if (products.contains(updated_products_items[i][0])) {
				updated_products_items[i][1] = 0;
			} else {
				updated_products_items[i][1] = getAllIncomingOrderInformationInTheSystem().get(order).get(3).get(i);
			}
		}
		return updated_products_items;
	}
    
    
	public boolean updateIncomingOrderDeleteProducts(IncomingOrder order, ArrayList<Product> productsInTheSystem,
			Scanner scanner, ArrayList<Shop> shopsInTheSystem, ArrayList<Stock> stockOfProductsInTheSystem) {

		ArrayList<Integer> products = chooseProductsToDelete(order, productsInTheSystem, scanner);
		int deleted_from_order_products[][] = getDeletedFromOrderProducts(products, order);
		int updated_products_items[][] = getUpdatedProductsItems(products, order);

		// total cost before discount
		double total_cost = calculateIncomingOrderTotalCost(updated_products_items, productsInTheSystem);
		// discount
		double discount = order.getOrder_discount();

		boolean order_finally_deleted = false;
		if (submitUpdate(updated_products_items, productsInTheSystem, shopsInTheSystem, order, scanner, discount)) {
			// update order
			for (int i = 0; i < products.size(); i++) {
				getAllIncomingOrderInformationInTheSystem().get(order).get(3).set(i, 0);
			}
			boolean delete_order = false; // in case you choose "2" in edit and you delete all the products of that
											// order
			for (int it : getAllIncomingOrderInformationInTheSystem().get(order).get(3)) {
				if (it != 0) {
					delete_order = false;
					break;
				} else {
					delete_order = true;
				}
			}
			if (delete_order) {
				getAllIncomingOrderInformationInTheSystem().remove(order);
				order_finally_deleted = true;
			} else {
				order.setTotal_cost(total_cost - total_cost * discount);
			}
			// update stock
			StockDAO stdao = new StockDAO();
			stdao.increaseStock(stockOfProductsInTheSystem, deleted_from_order_products);
		}
		return order_finally_deleted;
	}

	public void updateIncomingOrderDelete(IncomingOrder order, Scanner scanner,
			ArrayList<Stock> stockOfProductsInTheSystem) {
		FacadeOrder fo = new FacadeOrder();
		if (fo.approveFlow(scanner, "Submit", "Back")) {
			ArrayList<Integer> products = getAllIncomingOrderInformationInTheSystem().get(order).get(2);
			ArrayList<Integer> items = getAllIncomingOrderInformationInTheSystem().get(order).get(3);
			int products_items[][] = convertArrayListInto2DTable(products, items);
			// update order
			getAllIncomingOrderInformationInTheSystem().remove(order);
			// update stock
			StockDAO stdao = new StockDAO();
			stdao.increaseStock(stockOfProductsInTheSystem, products_items);
		}
	}
    
	public void updateIncomingOrderDiscount(IncomingOrder order, Scanner scanner,
			ArrayList<Product> productsInTheSystem, ArrayList<Shop> shopsInTheSystem) {
		System.out.print("Enter the new discount:");
		double new_discount = enterDiscount(scanner);
		ArrayList<Integer> products = getAllIncomingOrderInformationInTheSystem().get(order).get(2);
		ArrayList<Integer> items = getAllIncomingOrderInformationInTheSystem().get(order).get(3);
		int products_items[][] = convertArrayListInto2DTable(products, items);
		if (submitUpdate(products_items, productsInTheSystem, shopsInTheSystem, order, scanner, new_discount)) {
			// update order
			order.setOrder_discount(new_discount);
			double total_cost = order.getTotal_cost();
			order.setTotal_cost(total_cost - total_cost * new_discount);
		}
	}

	public int[][] convertArrayListInto2DTable(ArrayList<Integer> a, ArrayList<Integer> b) {
		int products_items[][] = new int[a.size()][2];
		for (int i = 0; i < a.size(); i++) {
			products_items[i][0] = a.get(i);
			products_items[i][1] = b.get(i);
		}
		return products_items;
	}
    
    
	public void updateIncomingOrderStatus(IncomingOrder order, Scanner scanner) {
		if (order.getOrder_status().equals("Pending")) {
			String status_options = "Packed or Shipped";
			submitNewStatus(order, scanner, status_options);
		} else {
			String status_options = "Shipped";
			submitNewStatus(order, scanner, status_options);
		}
	}

	private void submitNewStatus(IncomingOrder order, Scanner scanner, String status_options) {
		System.out.println("Enter new status\n(" + status_options + ")");
		String new_status = null;
		boolean repeat = true;
		while (repeat) {
			try {
				new_status = scanner.nextLine();
				if (!new_status.equals("Packed") && !new_status.equals("Shipped")) {
					throw new Exception("You should enter only: " + status_options);
				} else {
					repeat = false;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("The order is about to change to: " + new_status);
		FacadeOrder fo = new FacadeOrder();
		if (fo.approveFlow(scanner, "Submit", "Back")) {
			ArrayList<ArrayList<Integer>> order_info = getAllIncomingOrderInformationInTheSystem().get(order);
			getAllIncomingOrderInformationInTheSystem().remove(order); // i have to save my changes
			order.setOrder_status(new_status); // in the hashmap !
			getAllIncomingOrderInformationInTheSystem().put(order, order_info);
		}
	}
    

}


