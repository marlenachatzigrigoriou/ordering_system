package facade;

import java.util.ArrayList;
import java.util.Scanner;

import application.Order;
import application.Product;
import application.Stock;
import application.Supplier;
import application.User;
import applicationDAO.ProductDAO;
import applicationDAO.StockDAO;

/**
 * The Facade of the Product class.
 * 
 * @author marlenachatzigrigoriou
 */
public class FacadeProduct {

	/**
	 * Prints a detailed product description.
	 * 
	 * @param productsInTheSystem the stored in the memory products
	 */
	public void listProducts(ArrayList<Product> productsInTheSystem) {
		for (Product product : productsInTheSystem) {
			System.out.println(product.getProduct_id() + " | " + product.getProduct_name() + " , "
					+ product.getProduct_size() + " inches , " + product.getProduct_weight() + " kg , "
					+ product.getProduct_metal() + " , " + product.getProduct_price() + "€ ");
		}
	}

	/**
	 * Insert the products and their items for a given order.
	 * 
	 * @param scanner                    scanner
	 * @param productsInTheSystem        the stored in the memory products
	 * @param user_obj                   User object
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @param outgoingOrdersInTheSystem  all the outgoing orders stored in the
	 *                                   memory
	 * @param suppliersInTheSystem       the stored in the memory suppliers
	 * @return the products and items added in the given order
	 */
	public int[][] addProductsInOrder(Scanner scanner, ArrayList<Product> productsInTheSystem, User user_obj,
			ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem) {
		int[][] products_items = new int[productsInTheSystem.size()][2];
		for (int i = 0; i < productsInTheSystem.size(); i++) {
			products_items[i][0] = productsInTheSystem.get(i).getProduct_id();
		}
		listProducts(productsInTheSystem);
		boolean add_product = true;
		ProductDAO pdao = new ProductDAO();
		FacadeOrder fo = new FacadeOrder();
		while (add_product) {
			int product_id = pdao.chooseProduct(productsInTheSystem, scanner); // only the existent ids
			int item_quantity = pdao.chooseItemsForProduct(scanner, product_id, stockOfProductsInTheSystem);
			if (user_obj.getCategory().equals("Salesman")) {
				StockDAO stdao = new StockDAO();
				if (stdao.checkStockCapacity(product_id, item_quantity, stockOfProductsInTheSystem, productsInTheSystem,
						outgoingOrdersInTheSystem, suppliersInTheSystem)) {
					products_items[product_id][1] += item_quantity;
					add_product = fo.approveFlow(scanner, "Add product", "Next");
				} else {
					System.out.println("not enough stock");
					add_product = true;
				}
			} else { // warehouse
				products_items[product_id][1] += item_quantity;
				System.out.println("Next   |   Add product");
				add_product = fo.approveFlow(scanner, "Add product", "Next");
			}
		}
		return products_items;
	}
}