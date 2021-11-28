package Facade;

import java.util.ArrayList;

import application.Product;
import application.Stock;

public class FacadeStock {
	
    public void listStock(ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Product> productsInTheSystem){
        for (Stock stock : stockOfProductsInTheSystem){
            System.out.println("Product: " + productsInTheSystem.get(stock.getStock_id()).getProduct_name() + ", Stock: " + stock.getStock_quantity());
        }
    }

}