package application;

public class Stock {

	private int stock_id;
	private int stock_quantity;
	private int stock_lower_limit = 5;
	private int standard_quantity_request;

	public Stock(int stock_id, int stock_quantity, int standard_quantity_request) {
		this.stock_id = stock_id;
		this.stock_quantity = stock_quantity;
		this.standard_quantity_request = standard_quantity_request;
	}

	public Stock() {};

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public int getStock_lower_limit() {
		return stock_lower_limit;
	}

	public void setStock_lower_limit(int stock_lower_limit) {
		this.stock_lower_limit = stock_lower_limit;
	}

	public int getStandard_quantity_request() {
		return standard_quantity_request;
	}

	public void setStandard_quantity_request(int standard_product_stock) {
		this.standard_quantity_request = standard_product_stock;
	}
	
    //Test equal, override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return stock_id == stock.stock_id &&
                stock_lower_limit == stock.stock_lower_limit && stock_quantity == stock.stock_quantity 
                && standard_quantity_request == stock.standard_quantity_request;
    }

}
