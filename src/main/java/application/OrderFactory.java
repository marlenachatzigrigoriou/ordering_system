package application;

import java.util.Date;

/**
 * The factory of Order class, that generates Order objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class OrderFactory {

	/**
	 * Object of class Order.
	 */
	private Order order;

	/**
	 * Generates Order objects, depending on the defined order category.
	 * 
	 * @param total_cost     the total cost of the order.
	 * @param order_date     the date the order was created/submitted.
	 * @param order_discount the discount that is applied on the cost of the order.
	 * @param order_category the category (incoming or outgoing) of the Order
	 *                       object.
	 * @return order Order object.
	 */
	public Order createOrder(double total_cost, Date order_date, double order_discount, String order_category) {

		if (order_category.equals("Incoming")) {
			order = new IncomingOrder(total_cost, order_date, order_discount);
		} else if (order_category.equals("Outgoing")) {
			order = new OutgoingOrder(total_cost, order_date, order_discount);
		}
		return order;
	}

}