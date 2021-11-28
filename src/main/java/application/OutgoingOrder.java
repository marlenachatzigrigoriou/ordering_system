package application;

import java.util.Date;

public class OutgoingOrder extends Order {

	// we assume that outgoing order can have discount --> defined by supplier
	// (default = 0) and of course has status --> again updated by the supplier side
	public OutgoingOrder(double total_cost, Date create_order_date, double order_discount) {
		super(total_cost, create_order_date, order_discount);
	}

}
