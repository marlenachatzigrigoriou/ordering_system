package facade;

import java.util.ArrayList;

import application.Shop;
import applicationDAO.ShopDAO;

/**
 * The Facade of the Shop class.
 * 
 * @author marlenachatzigrigoriou
 */
public class FacadeShop {

	/**
	 * Lists the the shops.
	 * 
	 * @param shopsInTheSystem the stored in the memory shops
	 */
	public void listShops(ArrayList<Shop> shopsInTheSystem) {
		ShopDAO shdao = new ShopDAO();
		for (Shop shop : shopsInTheSystem) {
			shdao.viewShop(shop);
		}
	}

}