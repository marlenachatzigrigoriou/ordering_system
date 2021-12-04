package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Order;

public abstract class OrderDAO {

	/**
	 * Stores the order in the memory.
	 * 
	 * @param ordersInTheSystem the orders stored in the memory
	 * @param o                 the order to be stored
	 * @return all the orders stored in the memory
	 */
	public abstract ArrayList<Order> storeOrdersInMemory(ArrayList<Order> ordersInTheSystem, Order o);

	/**
	 * Returns the order ids of the orders that the user with the user_id has
	 * submitted.
	 * 
	 * @param user_id the id of the user
	 * @return a list of order ids that the user has submitted
	 */
	public abstract ArrayList<Integer> getOrdersByUserId(int user_id);

	/**
	 * Stores in the HashMap all order's information.
	 * 
	 * @param order        the Order object to be stored
	 * @param user_id      the user_id of the user that submitted the order
	 * @param s_id         suplier or shop id
	 * @param products_ids the product ids
	 * @param items_ids    the item ids of the corresponding products
	 */
	public abstract void storeAllOrderInformationInMemory(Order order, ArrayList<Integer> user_id,
			ArrayList<Integer> s_id, ArrayList<Integer> products_ids, ArrayList<Integer> items_ids);

	/**
	 * Display the order's details.
	 * 
	 * @param order Order object
	 */
	public void viewOrder(Order order) {
		System.out.println("ID:  " + order.getOrder_id() + " | Status: " + order.getOrder_status() + ", Date: "
				+ order.getOrder_date() + ", Total_cost: " + order.getTotal_cost()
				+ "€ (discount factored in total cost: " + order.getOrder_discount() + ")");
	}

	/**
	 * Displays the information of the stored orders, only the Order's object info,
	 * not products, shops/suppliers etc.
	 * 
	 * @param ordersInTheSystem the orders stored in the memory
	 */
	public void listOrders(ArrayList<Order> ordersInTheSystem) { //
		for (Order order : ordersInTheSystem) {
			viewOrder(order);
		}
	}

	/**
	 * Returns the Order object with the given order id.
	 * 
	 * @param order_id          the Order's id
	 * @param ordersInTheSystem the orders stored in the memory
	 * @return the Order object
	 */
	public Order getOrderByOrderId(int order_id, ArrayList<Order> ordersInTheSystem) {
		for (Order o : ordersInTheSystem) {
			if (order_id == o.getOrder_id()) {
				return o;
			}
		}
		return null;
	}

	/**
	 * Enter the order's discount.
	 * 
	 * @param scanner scanner
	 * @return discount
	 */
	public double enterDiscount(Scanner scanner) {
		boolean repeat = true;
		String d;
		double new_discount = 0;
		while (repeat) {
			try {
				d = scanner.nextLine();
				new_discount = Double.parseDouble(d);
				if (new_discount < 0.0 || new_discount > 1.0) {
					throw new Exception("You should enter only a number > 0 and < 1.");
				} else {
					repeat = false;
					return new_discount;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return new_discount;
	}

}
