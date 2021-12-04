package application;

import java.util.Date;

/**
 * Incoming order class that inherits from the Order one.
 * 
 * @author marlenachatzigrigoriou
 */
public class IncomingOrder extends Order {

	/**
	 * Constructor class. Inherits from the Order constructor.
	 * 
	 * @param total_cost     the total cost of the order.
	 * @param order_date     the date the order was created/submitted.
	 * @param order_discount the discount that is applied on the cost of the order.
	 */
	public IncomingOrder(double total_cost, Date order_date, double order_discount) {
		super(total_cost, order_date, order_discount);
	}

}
