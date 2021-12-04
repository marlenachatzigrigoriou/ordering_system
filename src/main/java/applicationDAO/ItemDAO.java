package applicationDAO;

import java.util.ArrayList;
import application.Item;

/**
 * Item DAO class that contains the process of the Item objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class ItemDAO {

	/**
	 * Stores the given item in the memory.
	 * 
	 * @param itemsInTheSystem the items of the products stored in the system
	 * @param i                Item object
	 */
	public void storeItemsInMemory(ArrayList<Item> itemsInTheSystem, Item i) {
		itemsInTheSystem.add(i);
	}

	/**
	 * Returns the Item objects with the given item id.
	 * 
	 * @param item_id          the given item id
	 * @param itemsInTheSystem the items of the products stored in the system
	 * @return the Item object
	 */
	public Item getItemById(int item_id, ArrayList<Item> itemsInTheSystem) {
		for (Item item : itemsInTheSystem) {
			if (item.getItem_id() == item_id) {
				return item;
			}
		}
		return null;
	}

}
