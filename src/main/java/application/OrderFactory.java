package application;

import java.util.Date;

public class OrderFactory {

	Order order;

	public Order createOrder(double total_cost, Date order_date, double order_discount, String order_category) {

		if (order_category.equals("Incoming")) {
			order = new IncomingOrder(total_cost, order_date, order_discount);
		} else if (order_category.equals("Outgoing")) {
			order = new OutgoingOrder(total_cost, order_date, order_discount);
		}
		return order;
	}

}