package Facade;

import java.util.ArrayList;

import application.Shop;
import applicationDAO.ShopDAO;

public class FacadeShop {
    public void listShops(ArrayList<Shop> shopsInTheSystem) {
    	ShopDAO shdao = new ShopDAO();
        for (Shop shop : shopsInTheSystem){
            shdao.viewShop(shop);
        }
    }


}