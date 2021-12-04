package application;

/**
 * Includes the information and data access functions about a product's stock.
 * 
 * @author marlenachatzigrigoriou
 */
public class Stock {

	/**
	 * The id of the stock. In this class, the id shouldn;t be auto-generated,
	 * because the stock will correspond to the products. Whenever we create a new
	 * product, a new stock gets created, with the stock-id to be the same with the
	 * product id.
	 */
	private int stock_id;

	/**
	 * The quantity of the stock.
	 */
	private int stock_quantity;

	/**
	 * The lower limit that the stock can reach.
	 */
	private int stock_lower_limit = 5;

	/**
	 * The standard quantity that will be requested through an automated outgoing
	 * ordered when the stock get lower than stock_lower_limit.
	 */
	private int standard_quantity_request;

	/**
	 * Constructor class.
	 * 
	 * @param stock_id                  the stock's id
	 * @param stock_quantity            thw stock's quantity
	 * @param standard_quantity_request the stock's standard quantity request for
	 *                                  outgoing orders.
	 */
	public Stock(int stock_id, int stock_quantity, int standard_quantity_request) {
		this.stock_id = stock_id;
		this.stock_quantity = stock_quantity;
		this.standard_quantity_request = standard_quantity_request;
	}

	/**
	 * Empty constructor.
	 */
	public Stock() {
	};

	/**
	 * Getter function of the stock id.
	 * 
	 * @return stock's id
	 */
	public int getStock_id() {
		return stock_id;
	}

	/**
	 * Setter function of the stock id.
	 * 
	 * @param stock_id the id of the stock.
	 */
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	/**
	 * Getter function of the stock quantity.
	 * 
	 * @return stock's quantity
	 */
	public int getStock_quantity() {
		return stock_quantity;
	}

	/**
	 * Setter function of the stock id.
	 * 
	 * @param stock_quantity the quantity of the stock.
	 */
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	/**
	 * Getter function of the stock lower limit.
	 * 
	 * @return stock's lower limit
	 */
	public int getStock_lower_limit() {
		return stock_lower_limit;
	}

	/**
	 * Setter function of the stock lower limit.
	 * 
	 * @param stock_lower_limit the lower limit of the stock.
	 */
	public void setStock_lower_limit(int stock_lower_limit) {
		this.stock_lower_limit = stock_lower_limit;
	}

	/**
	 * Getter function of the stock standard quantity request.
	 * 
	 * @return stock's standard quantity request
	 */
	public int getStandard_quantity_request() {
		return standard_quantity_request;
	}

	/**
	 * Setter function of the stock standard quantity request.
	 * 
	 * @param standard_quantity_request the standard quantity request of the
	 *                                  product.
	 */
	public void setStandard_quantity_request(int standard_product_stock) {
		this.standard_quantity_request = standard_product_stock;
	}

	// Test equal, override equals() and hashCode()
	/**
	 * Defines the equal function between two objects of the Stock class, and check
	 * if they are equal.
	 * 
	 * @param o a Stock object.
	 *
	 * @return a true or false value
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Stock stock = (Stock) o;
		return stock_id == stock.stock_id && stock_lower_limit == stock.stock_lower_limit
				&& stock_quantity == stock.stock_quantity
				&& standard_quantity_request == stock.standard_quantity_request;
	}

}
