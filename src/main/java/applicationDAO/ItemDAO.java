package applicationDAO;

import java.util.ArrayList;
import application.Item;


public class ItemDAO {
    
    public void storeItemsInMemory(ArrayList<Item> itemsInTheSystem, Item i) {
        itemsInTheSystem.add(i);
    }
    
    public Item getItemById(int item_id, ArrayList<Item> itemsInTheSystem){
        for (Item item : itemsInTheSystem){
            if (item.getItem_id() == item_id){
                return item;
            }
        }
        return null;
    }
    
}
