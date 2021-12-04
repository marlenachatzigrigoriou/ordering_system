package facade;

import java.util.ArrayList;
import application.Product;
import application.Stock;

/**
 * The Facade of the Stock class.
 * 
 * @author marlenachatzigrigoriou
 */
public class FacadeStock {

	/**
	 * Lists the products stock.
	 * 
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param productsInTheSystem        the stored in the memory products
	 */
	public void listStock(ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Product> productsInTheSystem) {
		for (Stock stock : stockOfProductsInTheSystem) {
			System.out.println("Product: " + productsInTheSystem.get(stock.getStock_id()).getProduct_name()
					+ ", Stock: " + stock.getStock_quantity());
		}
	}

}