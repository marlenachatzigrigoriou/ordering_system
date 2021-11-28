package application;

import java.util.Date;

public abstract class Order {

	private int order_id;
	private double total_cost;
	private Date order_date;
	private String order_status;
	private double order_discount;
	private static int count = 0;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Order.count = count;
	}

	// we assume that status doesn't need to be in the parameters as whenever you
	// make a new order, it will be pending
	public Order(double total_cost, Date order_date, double order_discount) {
		this.order_id = count++;
		this.total_cost = -total_cost * order_discount + total_cost;
		this.order_date = order_date;
		this.order_discount = order_discount;
		this.setOrder_status("Pending");
	}

	public int getOrder_id() {
		return this.order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getTotal_cost() {
		return this.total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public Date getOrder_date() {
		return this.order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public double getOrder_discount() {
		return order_discount;
	}

	public void setOrder_discount(double order_discount) {
		this.order_discount = order_discount;
	}

    //Test equal, override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  order_status == order.order_status && order_discount == order.order_discount 
                && order_date == order.order_date && total_cost == order.total_cost; 
    }
    
    
}