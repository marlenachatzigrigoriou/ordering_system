package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Shop;
import facade.FacadeShop;

/**
 * Shop DAO class that contains the process of the Shop objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class ShopDAO {

	/**
	 * Stores the given shop in the memory.
	 * 
	 * @param shopsInTheSystem the stored in the memory shops
	 * @param s                the given shop
	 */
	public void storeShopsInMemory(ArrayList<Shop> shopsInTheSystem, Shop s) {
		shopsInTheSystem.add(s);
	}

	/**
	 * Prints the given shop details.
	 * 
	 * @param shop the given Shop object
	 */
	public void viewShop(Shop shop) {
		System.out.println(shop.getShop_id() + " | " + shop.getShop_name() + ", Address: " + shop.getShop_address()
				+ ", Manager: " + shop.getShop_manager() + ", Industry: " + shop.getShop_industry_code());
	}

	/**
	 * Returns the shop with the given shop id.
	 * 
	 * @param shop_id          the given shop's id
	 * @param shopsInTheSystem the stored in the memory shops
	 * @return the Shop object
	 */
	public Shop getShopById(int shop_id, ArrayList<Shop> shopsInTheSystem) {
		for (Shop shop : shopsInTheSystem) {
			if (shop.getShop_id() == shop_id) {
				return shop;
			}
		}
		return null;
	}

	/**
	 * Choose shop.
	 * 
	 * @param shopsInTheSystem the stored in the memory shops
	 * @param scanner          scanner
	 * @return the chosen shop
	 */
	public int chooseShop(ArrayList<Shop> shopsInTheSystem, Scanner scanner) {
		System.out.println("Available shops: ");
		FacadeShop fs = new FacadeShop();
		fs.listShops(shopsInTheSystem);
		ArrayList<Integer> shops_ids = new ArrayList<Integer>();
		for (Shop s : shopsInTheSystem) {
			shops_ids.add(s.getShop_id());
		}
		int shop_id = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println("Enter the shop id for which you wish to see its orders: ");
				shop_id = scanner.nextInt();
				scanner.nextLine();
				if (shops_ids.contains(shop_id)) {
					repeat = false;
				} else {
					throw new Exception("ERROR: you should enter an ID from the ones already on the screen.");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return shop_id;
	}

}
