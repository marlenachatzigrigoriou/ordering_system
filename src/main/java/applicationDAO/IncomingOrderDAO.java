package applicationDAO;

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import application.Product;
import application.Shop;
import application.Stock;
import application.User;
import facade.FacadeOrder;
import application.Order;
import application.OrderFactory;
import application.IncomingOrder;

/**
 * Incoming order DAO class that contains the process of the IncomingOrder
 * objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class IncomingOrderDAO extends OrderDAO {
	/**
	 * The HashMap contains in each (order_id=)key, all the required ids to define a
	 * whole order, ex. (incoming_order, [[salesman_id], [shop_id], [products_ids],
	 * [items_ids]])
	 */
	private static HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>> allIncomingOrderInformationInTheSystem = new HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>>();

	/**
	 * Getter method of allIncomingOrderInformationInTheSystem.
	 * 
	 * @return allIncomingOrderInformationInTheSystem
	 */
	public HashMap<IncomingOrder, ArrayList<ArrayList<Integer>>> getAllIncomingOrderInformationInTheSystem() {
		return allIncomingOrderInformationInTheSystem;
	}

	/**
	 * Stores the incoming order in the memory.
	 * 
	 * @param incomingOrdersInTheSystem all the incoming orders stored in the memory
	 * @param o                         the incoming order to be stored
	 * @return all the incoming orders stored in the memory
	 */
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

	/**
	 * Stores in the allIncomingOrderInformationInTheSystem HashMap all incoming
	 * order's information.
	 * 
	 * @param order        the Order object to be stored
	 * @param salesman_id  the user_id of the user that submitted the order
	 * @param shop_id      the shop_id that the order is for
	 * @param products_ids the product ids of the products included in the order
	 * @param items_ids    the item ids of the items that correspond to the products
	 */
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

	/**
	 * Prints all the details (order, products, items, costs, shop, salesman) of
	 * each stored in the memory incoming order.
	 * 
	 * @param productsInTheSystem the stored in the memory products
	 * @param usersInTheSystem    the stored in the memory users
	 * @param shopsInTheSystem    the stored in the memory shops
	 */
	public void listAllIncomingOrderInformation(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem) {
		for (IncomingOrder i : getAllIncomingOrderInformationInTheSystem().keySet()) {
			viewIncomingOrderInformation(i, productsInTheSystem, usersInTheSystem, shopsInTheSystem);
		}
	}

	/**
	 * Prints all the details (name, items, final cost) for each product in the
	 * incoming order.
	 * 
	 * @param productsInTheSystem the stored in the memory products
	 * @param i                   the IncomingOrder object
	 */
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

	/**
	 * Prints all the details (order, products, items, costs, shop, salesman) of the
	 * incoming order.
	 * 
	 * @param i                   the IncomingOrder object
	 * @param productsInTheSystem the stored in the memory products
	 * @param usersInTheSystem    the stored in the memory users
	 * @param shopsInTheSystem    the stored in the memory shops
	 */
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

	/**
	 * Prints a summary (products, items, final cost, shop) of the given incoming
	 * order.
	 * 
	 * @param products_items      a 2-dimensional array; product ids in the first
	 *                            column, item ids corresponding to the product ones
	 *                            in the second column
	 * @param discount            the discount on the incoming order
	 * @param shop_id             the shop id of the shop for which the order is for
	 * @param productsInTheSystem the stored in the memory products
	 * @param shopsInTheSystem    the stored in the memory shops
	 */
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

	/**
	 * Prints all the orders that were submitted for the selected shop.
	 * 
	 * @param productsInTheSystem       the stored in the memory products
	 * @param usersInTheSystem          the stored in the memory users
	 * @param shopsInTheSystem          the stored in the memory shops
	 * @param incomingOrdersInTheSystem all the incoming orders stored in the memory
	 * @param scanner                   scanner
	 */
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

	/**
	 * Prints all the orders that were submitted by the given salesman.
	 *
	 * @param productsInTheSystem       the stored in the memory products
	 * @param usersInTheSystem          the stored in the memory users
	 * @param shopsInTheSystem          the stored in the memory shops
	 * @param incomingOrdersInTheSystem all the incoming orders stored in the memory
	 * @param user_obj                  the salesman
	 * @return the order ids of the orders submitted by the given salesman
	 */
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

	/**
	 * Creates the IncomingOrder object and stores it in memory.
	 * 
	 * @param discount                  the discount on the incoming order
	 * @param products_items            a 2-dimensional array; product ids in the
	 *                                  first column, item ids corresponding to the
	 *                                  product ones in the second column * @param
	 *                                  productsInTheSystem
	 * @param incomingOrdersInTheSystem all the incoming orders stored in the memory
	 * @return the created IncomingOrder object
	 */
	public IncomingOrder submitIncomingOrder(double order_discount, int[][] products_items,
			ArrayList<Product> productsInTheSystem, ArrayList<Order> incomingOrdersInTheSystem) {
		double total_cost = calculateIncomingOrderTotalCost(products_items, productsInTheSystem);
		Date date = Calendar.getInstance().getTime();
		OrderFactory ofac = new OrderFactory();
		// the final total_cost (calculated with the discount) is generated here!
		IncomingOrder ino = (IncomingOrder) ofac.createOrder(total_cost, date, order_discount, "Incoming");
		storeOrdersInMemory(incomingOrdersInTheSystem, ino);
		return ino;
	}

	/**
	 * Submits the incoming order, stores it with its details in the
	 * allIncomingOrderInformationInTheSystem HashMap and reduces the stock.
	 * 
	 * @param discount                   the discount on the incoming order
	 * @param salesman_id                the user_id of the user that submitted the
	 *                                   order
	 * @param shop_id                    the shop id of the shop for which the order
	 *                                   is for
	 * @param products_items             a 2-dimensional array; product ids in the
	 *                                   first column, item ids corresponding to the
	 *                                   product ones in the second column 
	 * @param productsInTheSystem        the stored in the memory products
	 * @param incomingOrdersInTheSystem  all the incoming orders stored in the
	 *                                   memory
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @return the created IncomingOrder object
	 */
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
		StockDAO stdao = new StockDAO();
		storeAllOrderInformationInMemory(order, salesman, shop, products_ids, items_ids);
		stdao.reduceStock(stockOfProductsInTheSystem, products_items);
		return order;
	}

	/**
	 * Return the order ids of the orders that were created for the shop that
	 * corresponds to the given shop id.
	 * 
	 * @param shop_id the shop id of the shop for which the order is for
	 * @return a list of the order ids
	 */
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

	/**
	 * Calculates the total cost of the order before the discount
	 * 
	 * @param products_items      a 2-dimensional array; product ids in the first
	 *                            column, item ids corresponding to the product ones
	 *                            in the second column
	 * @param productsInTheSystem the stored in the memory products
	 * @return order's the total cost
	 */
	public double calculateIncomingOrderTotalCost(int products_items[][], ArrayList<Product> productsInTheSystem) {
		// products_items = N x 2
		//
		// product_id | item_quantity
		// _____________________________
		// | 1 5 --> product+item
		// |
		// | 2 10 --> product+item
		//
		// ex.: products_items[0,0] & products_items[1,0] --> buy 20 items of product 1 --> 20 * (price of 1) = cost
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

	/**
	 * Submits the update to the incoming order.
	 * 
	 * @param products_items      a 2-dimensional array; product ids in the first
	 *                            column, item ids corresponding to the product ones
	 *                            in the second column
	 * @param productsInTheSystem the stored in the memory products
	 * @param shopsInTheSystem    the stored in the memory shops
	 * @param order               the IncomingOrder object
	 * @param s                   scanner
	 * @param discount            the discount on the incoming order
	 * @return true or false regarding submit
	 */
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

	/**
	 * Add products to an already existing incoming order.
	 * 
	 * @param order                      the IncomingOrder object
	 * @param new_products_items         a 2-dimensional array; product ids in the
	 *                                   first column, item ids corresponding to the
	 *                                   product ones in the second column
	 * @param productsInTheSystem        the stored in the memory products
	 * @param shopsInTheSystem           the stored in the memory shops
	 * @param s                          scanner
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 */
	public void updateIncomingOrderAddProducts(IncomingOrder order, int new_products_items[][],
			ArrayList<Product> productsInTheSystem, ArrayList<Shop> shopsInTheSystem, Scanner s,
			ArrayList<Stock> stockOfProductsInTheSystem) { // salesman cannot change shop_id, salesman_id, status, date.
															// only --> products, items, discount
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

	/**
	 * Choose the products that will be deleted form an already existing order.
	 * 
	 * @param order               the IncomingOrder object
	 * @param productsInTheSystem the stored in the memory products
	 * @param scanner             scanner
	 * @return the products ids of the products that will be deleted
	 */
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

	/**
	 * Save temporarily the products and their items that will be deleted, so they can be used to increase the stock
	 * later.
	 * 
	 * @param products the products ids of the products that will be deleted
	 * @param order    the IncomingOrder object
	 * @return the products ids and the item ids of the products that will be
	 *         deleted
	 */
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

	/**
	 * Updates the item ids, of the deleted product ids, to be equal to 0.
	 * 
	 * @param products the products ids of the products that will be deleted
	 * @param order    the IncomingOrder object
	 * @return the products ids and the item ids of the products of the updated
	 *         order
	 */
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

	/**
	 * Deletes the selected products from the given incoming order. If the user
	 * deletes all the products the total order will be deleted.
	 * 
	 * @param order                      the IncomingOrder object
	 * @param productsInTheSystem        the stored in the memory products
	 * @param scanner                    scanner
	 * @param shopsInTheSystem           the stored in the memory shops
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @return true or false whether the order was finally deleter or not
	 */
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

	/**
	 * Deletes the selected incoming order.
	 * 
	 * @param order                      the IncomingOrder object
	 * @param scanner                    scanner
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 */
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

	/**
	 * Updates the discount in the selected order.
	 * 
	 * @param order               the IncomingOrder object
	 * @param scanner             scanner
	 * @param productsInTheSystem the stored in the memory products
	 * @param shopsInTheSystem    the stored in the memory shops
	 */
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

	/**
	 * Converts 2 ArrayLists into a 2 dimensional Table.
	 * 
	 * @param a represents the 1st column of the 2D table
	 * @param b represents the 2nd column of the 2D table
	 * @return a 2D integer table
	 */
	public int[][] convertArrayListInto2DTable(ArrayList<Integer> a, ArrayList<Integer> b) {
		int products_items[][] = new int[a.size()][2];
		for (int i = 0; i < a.size(); i++) {
			products_items[i][0] = a.get(i);
			products_items[i][1] = b.get(i);
		}
		return products_items;
	}

	/**
	 * Updates the status of the incoming order. This authority have both the
	 * warehouse staff and the manager.
	 * 
	 * @param order   the IncomingOrder object
	 * @param scanner scanner
	 */
	public void updateIncomingOrderStatus(IncomingOrder order, Scanner scanner) {
		if (order.getOrder_status().equals("Pending")) {
			String status_options = "Packed or Shipped";
			submitNewStatus(order, scanner, status_options);
		} else {
			String status_options = "Shipped";
			submitNewStatus(order, scanner, status_options);
		}
	}

	/**
	 * Submits the updated status to the given incoming order.
	 * 
	 * @param order          the IncomingOrder object
	 * @param scanner        scanner
	 * @param status_options the available values with which the status can be
	 *                       updated
	 */
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
			getAllIncomingOrderInformationInTheSystem().remove(order); // i have to save my changes in the HashMap !
			order.setOrder_status(new_status); 						  
			getAllIncomingOrderInformationInTheSystem().put(order, order_info);
		}
	}

}
