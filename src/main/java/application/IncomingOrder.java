package application;

import java.util.Date;

public class IncomingOrder extends Order {

	public IncomingOrder(double total_cost, Date order_date, double order_discount) {
		super(total_cost, order_date, order_discount);
	}

}
