package application;

import java.util.Date;

/**
 * Includes the information and data access functions about an order. An order
 * can be incoming or outgoing.
 * 
 * @author marlenachatzigrigoriou
 */
public abstract class Order {

	/**
	 * The auto-generated id of the order.
	 */
	private int order_id;

	/**
	 * The total cost of an order, factored with the discount.
	 */
	private double total_cost;

	/**
	 * The date of creation/submission of the order.
	 */
	private Date order_date;

	/**
	 * The status of the order.
	 */
	private String order_status;

	/**
	 * The discount that is assigned to the order.
	 */
	private double order_discount;

	/**
	 * A static counter that helps in the auto-generation of oder_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class.
	 * 
	 * @param total_cost     the total cost of the order.
	 * @param order_date     the date the order was created/submitted.
	 * @param order_discount the discount that is applied on the cost of the order.
	 */
	public Order(double total_cost, Date order_date, double order_discount) {
		this.order_id = count++;
		this.total_cost = -total_cost * order_discount + total_cost;
		this.order_date = order_date;
		this.order_discount = order_discount;
		this.setOrder_status("Pending");
	}

	/**
	 * Getter function of the order id.
	 *
	 * @return order's id
	 */
	public int getOrder_id() {
		return this.order_id;
	}

	/**
	 * Setter function of the order id.
	 * 
	 * @param order_id the auto-generated id of the order.
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	/**
	 * Getter function of the order total cost.
	 *
	 * @return order's total cost
	 */
	public double getTotal_cost() {
		return this.total_cost;
	}

	/**
	 * Setter function of the order total cost.
	 * 
	 * @param total_cost the total cost of the order.
	 */
	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	/**
	 * Getter function of the order creation/submission date.
	 *
	 * @return order's submission/creation date
	 */
	public Date getOrder_date() {
		return this.order_date;
	}

	/**
	 * Setter function of the order creation/submission date.
	 * 
	 * @param order_date the date the order was created/submitted.
	 */
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	/**
	 * Getter function of the order status.
	 *
	 * @return order's status
	 */
	public String getOrder_status() {
		return order_status;
	}

	/**
	 * Setter function of the order status.
	 * 
	 * @param order_status the status of the order.
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	/**
	 * Getter function of the order discount.
	 *
	 * @return order's discount
	 */
	public double getOrder_discount() {
		return order_discount;
	}

	/**
	 * Setter function of the order total discount.
	 * 
	 * @param order_discount the discount that is applied on the cost of the order.
	 */
	public void setOrder_discount(double order_discount) {
		this.order_discount = order_discount;
	}

	/**
	 * Setter function of the counter. Only needed for the correct
	 * implementation of the unit tests.
	 * 
	 * @param count the counter that helps in the auto-generation of oder_id.
	 */
	public static void setCount(int count) {
		Order.count = count;
	}

	/**
	 * Defines the equal function between two objects of the Order class, 
	 * and check if they are equal.
	 * 
	 * @param o an Order object.
	 *
	 * @return a true or false value 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Order order = (Order) o;
		return order_status == order.order_status && order_discount == order.order_discount
				&& order_date == order.order_date && total_cost == order.total_cost;
	}

}