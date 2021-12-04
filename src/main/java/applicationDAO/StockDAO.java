package applicationDAO;

import java.util.ArrayList;

import application.Order;
import application.Product;
import application.Stock;
import application.Supplier;

/**
 * Stock DAO class that contains the process of the Stock objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class StockDAO {

	/**
	 * Stores in the memory the given Stock object.
	 * 
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param s                          Stock object to be stored
	 * @return the stored in the system stock of the products
	 */
	public ArrayList<Stock> storeStockOfProductsInMemory(ArrayList<Stock> stockOfProductsInTheSystem, Stock s) {
		stockOfProductsInTheSystem.add(s);
		return stockOfProductsInTheSystem;
	}

	/**
	 * Returns the stock, which id is the one that is given.
	 * 
	 * @param id                         stock id
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @return the stock with the given stock id
	 */
	public Stock getStockByStockId(int id, ArrayList<Stock> stockOfProductsInTheSystem) {
		for (Stock s : stockOfProductsInTheSystem) {
			if (s.getStock_id() == id) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Updates the stock's lower limit.
	 * 
	 * @param new_limit updated lower limit
	 * @param stock     stock object that will be updated
	 * @return the updated lower limit of the stock
	 */
	public int updateStockLowerLimit(int new_limit, Stock stock) {
		stock.setStock_lower_limit(new_limit);
		return stock.getStock_lower_limit();
	}

	/**
	 * Reduces the stock of the given products.
	 * 
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param products_items             a 2-dimensional array; product ids in the
	 *                                   first column, item ids corresponding to the
	 *                                   product ones in the second column
	 * @return the stored in memory Stock objects
	 */
	public ArrayList<Stock> reduceStock(ArrayList<Stock> stockOfProductsInTheSystem, int[][] products_items) {
		for (int[] k : products_items) {
			int product_id = k[0];
			int stock_change = k[1]; // = items of product_id
			int current_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();

			stockOfProductsInTheSystem.get(product_id).setStock_quantity(current_stock - stock_change);
			// it can't get lower than 0, i have already checked it with checkStockCapacity
		}
		return stockOfProductsInTheSystem;
	}

	/**
	 * Increases the stock of the given products.
	 * 
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param products_items             a 2-dimensional array; product ids in the
	 *                                   first column, item ids corresponding to the
	 *                                   product ones in the second column
	 * @return the stored in memory Stock objects
	 */
	public ArrayList<Stock> increaseStock(ArrayList<Stock> stockOfProductsInTheSystem, int[][] products_items) {
		for (int[] k : products_items) {
			int product_id = k[0];
			int stock_change = k[1]; // = items of product_id
			int current_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();

			stockOfProductsInTheSystem.get(product_id).setStock_quantity(current_stock + stock_change);
		}
		return stockOfProductsInTheSystem;
	}

	/**
	 * Checks if the salesman can order the given items of the given product.
	 * 
	 * @param product_id                 the product id of the product which the
	 *                                   salesman adds in the incoming order
	 * @param items                      the items of the product that needs to be
	 *                                   ordered by a salesman for a shop
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param productsInTheSystem        the stored in the memory products
	 * @param outgoingOrdersInTheSystem  all the outgoing orders stored in the
	 *                                   memory
	 * @param suppliersInTheSystem       the stored in the memory suppliers
	 * @return
	 */
	public boolean checkStockCapacity(int product_id, int items, ArrayList<Stock> stockOfProductsInTheSystem,
			ArrayList<Product> productsInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem) {
		int product_stock = stockOfProductsInTheSystem.get(product_id).getStock_quantity();
		Stock s = new Stock();
		// ex.     17 		  18
		if ((product_stock - items < 0)) { // not enough stock for the order
			System.out.println("You can order the maximum of " + product_stock + " of this product!");
			return false;
			// ex.     17        -   16  <=     5
		} else if (product_stock - items <= s.getStock_lower_limit()) { // Proceed to the incoming order while making an
																		// AUTOMATED outgoing one
																		// i'm fine to get lower than 5 (lower limit)
																		// --> i just have, now, to make an outgoing
																		// order
			OutgoingOrderDAO oudao = new OutgoingOrderDAO();
			oudao.automatedOutgoingOrder(product_id, stockOfProductsInTheSystem, productsInTheSystem,
					outgoingOrdersInTheSystem, suppliersInTheSystem);
			return true;
		} else { // Proceed to the incoming order
			return true;
		}
	}

}
