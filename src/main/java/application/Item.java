package application;

/**
 * Includes the information and data access functions about the items of a
 * product.
 * 
 * @author marlenachatzigrigoriou
 */
public class Item {

	/**
	 * The auto-generated id of the item.
	 */
	private int item_id;

	/**
	 * The quantity of the item.
	 */
	private int quantity;

	/**
	 * A static counter that helps in the auto-generation of item_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class.
	 * 
	 * @param quantity the quantity of the item.
	 */
	public Item(int quantity) {
		this.item_id = count++;
		this.quantity = quantity;
	}

	/**
	 * Getter function of the item id.
	 * 
	 * @return item's id
	 */
	public int getItem_id() {
		return item_id;
	}

	/**
	 * Setter function of the item id.
	 * 
	 * @param item_id the auto-generated id of the item.
	 */
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	/**
	 * Getter function of the item quantity.
	 * 
	 * @return item's quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Setter function of the item quantity.
	 * 
	 * @param quantity the quantity of the item.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}