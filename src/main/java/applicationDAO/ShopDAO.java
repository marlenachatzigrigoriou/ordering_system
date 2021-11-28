package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import Facade.FacadeShop;
import application.Shop;

public class ShopDAO {

	public void storeShopsInMemory(ArrayList<Shop> shopsInTheSystem, Shop s) {
		shopsInTheSystem.add(s);
	}

	public void viewShop(Shop shop) {
		System.out.println(shop.getShop_id() + " | " + shop.getShop_name() + ", Address: " + shop.getShop_address()
				+ ", Manager: " + shop.getShop_manager() + ", Industry: " + shop.getShop_industry_code());
	}

	public Shop getShopById(int shop_id, ArrayList<Shop> shopsInTheSystem) {
		for (Shop shop : shopsInTheSystem) {
			if (shop.getShop_id() == shop_id) {
				return shop;
			}
		}
		return null;
	}

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
