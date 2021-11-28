package applicationDAO;

import java.util.ArrayList;

import application.Order;
import application.Product;
import application.Stock;
import application.Supplier;

public class StockDAO {

    public ArrayList<Stock> storeStockOfProductsInMemory(ArrayList<Stock> stockOfProductsInTheSystem, Stock s){
        stockOfProductsInTheSystem.add(s);
        return stockOfProductsInTheSystem;
    }

    public Stock getStockByStockId(int id, ArrayList<Stock> stockOfProductsInTheSystem) {
    	for (Stock s : stockOfProductsInTheSystem) {
    		if (s.getStock_id() == id) { 
    			return s;
    		}
    	}
    	return null;
    }
    
    public int updateStockLowerLimit(int new_limit, Stock stock){
        stock.setStock_lower_limit(new_limit);
        return stock.getStock_lower_limit();
    }

    public ArrayList<Stock> reduceStock(ArrayList<Stock> stockOfProductsInTheSystem, int[][] products_items){
        for (int[] k : products_items){
            int product_id = k[0];
            int stock_change = k[1]; // = items of product_id
            int current_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();

            stockOfProductsInTheSystem.get(product_id).setStock_quantity(current_stock - stock_change);
            // it can't get lower than 0, i have already checked it with checkStockCapacity
        }
        return stockOfProductsInTheSystem;
    }

    public ArrayList<Stock> increaseStock(ArrayList<Stock> stockOfProductsInTheSystem, int[][] products_items){
        for (int[] k : products_items){
            int product_id = k[0];
            int stock_change = k[1]; // = items of product_id
            int current_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();

            stockOfProductsInTheSystem.get(product_id).setStock_quantity(current_stock + stock_change);
        }
        return stockOfProductsInTheSystem;
    }
    
    public boolean checkStockCapacity(int product_id, int items, ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Product> productsInTheSystem, 
    																						ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Supplier> suppliersInTheSystem){
		 int product_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();
		 Stock s = new Stock();
		 //ex.      17         18      
		 if ((product_stock - items < 0) && items != 0) { //not enough stock for the order
			 System.out.println("You can order the maximum of " + product_stock + " of this product!");
			 return false;
		 //ex.           17      -     16   <=          5
		 } else if (  product_stock - items <= s.getStock_lower_limit()) { // Proceed to the incoming order while making an AUTOMATED outgoing one
			 															   // i'm fine to get lower than 5 (lower limit) --> i just have, now, to make an outgoing orde			 
			 OutgoingOrderDAO oudao = new OutgoingOrderDAO();
			 oudao.automatedOutgoingOrder(100000, product_id, stockOfProductsInTheSystem, productsInTheSystem, outgoingOrdersInTheSystem, suppliersInTheSystem);
			 return true;
		 } else { // Proceed to the incoming order
			 return true;
		 }
    }

}
