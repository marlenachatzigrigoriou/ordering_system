package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Order;

public abstract class OrderDAO {

	public abstract ArrayList<Order> storeOrdersInMemory(ArrayList<Order> ordersInTheSystem, Order o);

	public abstract ArrayList<Integer> getOrdersByUserId(int user_id);

	public abstract void storeAllOrderInformationInMemory(Order order, ArrayList<Integer> user_id,
			ArrayList<Integer> s_id, ArrayList<Integer> products_ids, ArrayList<Integer> items_ids);
	
	public void viewOrder(Order order) {
		System.out.println("ID:  " + order.getOrder_id() + " | Status: " + order.getOrder_status() + ", Date: "
				+ order.getOrder_date() + ", Total_cost: " + order.getTotal_cost()
				+ "€ (discount factored in total cost: " + order.getOrder_discount() + ")");
	}

	public void listOrders(ArrayList<Order> ordersInTheSystem) { // only order's object info, not products,
																	// shops/suppliers etc
		for (Order order : ordersInTheSystem) {
			viewOrder(order);
		}
	}

	public Order getOrderByOrderId(int order_id, ArrayList<Order> ordersInTheSystem) {
		for (Order o : ordersInTheSystem) {
			if (order_id == o.getOrder_id()) {
				return o;
			}
		}
		return null;
	}
	
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
