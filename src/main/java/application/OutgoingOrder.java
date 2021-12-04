package application;

import java.util.Date;

/**
 * Outgoing order class that inherits from the Order one.
 * 
 * @author marlenachatzigrigoriou
 */
public class OutgoingOrder extends Order {

	/**
	 * Constructor class. Inherits from the Order constructor.
	 * 
	 * @param total_cost     the total cost of the order.
	 * @param order_date     the date the order was created/submitted.
	 * @param order_discount the discount that is applied on the cost of the order.
	 */
	public OutgoingOrder(double total_cost, Date create_order_date, double order_discount) {
		super(total_cost, create_order_date, order_discount);
	}

}
