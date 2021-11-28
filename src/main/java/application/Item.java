package application;

public class Item {

	private int item_id;
	private int quantity;
	private static int count = 0;

	public Item(int quantity) {
		this.item_id = count++;
		this.quantity = quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}