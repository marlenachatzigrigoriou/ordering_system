package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.IncomingOrder;
import application.OutgoingOrder;
import application.Product;
import application.Stock;
import application.Supplier;
import facade.FacadeProduct;

/**
 * Product DAO class that contains the process of the Product objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class ProductDAO {

	/**
	 * Stores the products in the memory.
	 * 
	 * @param productsInTheSystem the stored in the memory products
	 * @param p                   the given product
	 * @return the products stored in the memory
	 */
	public ArrayList<Product> storeProductsInMemory(ArrayList<Product> productsInTheSystem, Product p) {
		productsInTheSystem.add(p);
		return productsInTheSystem;
	}

	/**
	 * Returns the product with the given id.
	 * 
	 * @param product_id          the given product id
	 * @param productsInTheSystem the stored in the memory products
	 * @return the Product object
	 */
	public Product getProductById(int product_id, ArrayList<Product> productsInTheSystem) {
		for (Product product : productsInTheSystem) {
			if (product.getProduct_id() == product_id) {
				return product;
			}
		}
		return null;
	}

	/**
	 * Prints the products of the given Incoming order.
	 * 
	 * @param order               the given Incoming order
	 * @param productsInTheSystem the stored in the memory products
	 * @return the Product objects
	 */
	public ArrayList<Product> listProductsOfOrder(IncomingOrder order, ArrayList<Product> productsInTheSystem) {
		ArrayList<Product> productsInOrder = new ArrayList<Product>();
		IncomingOrderDAO iodao = new IncomingOrderDAO();
		for (int i : iodao.getAllIncomingOrderInformationInTheSystem().get(order).get(2)) {
			Product p = getProductById(i, productsInTheSystem);
			productsInOrder.add(p);
		}
		FacadeProduct fp = new FacadeProduct();
		fp.listProducts(productsInOrder);
		return productsInOrder;
	}

	/**
	 * Prints the products and items of the given outgoing order.
	 * 
	 * @param productsInTheSystem  the stored in the memory products
	 * @param suppliersInTheSystem the stored in the memory suppliers
	 * @param supplier_id          the supplier_id of the supplier that provides the
	 *                             specific product
	 * @param i                    Outgoing order object
	 */
	public void printProductsOfOutgoingOrder(ArrayList<Product> productsInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, int supplier_id, OutgoingOrder i) {
		OutgoingOrderDAO oudao = new OutgoingOrderDAO();
		SupplierDAO sudao = new SupplierDAO();
		ArrayList<Integer> products_ids = oudao.getAllOutgoingOrderInformationInTheSystem().get(i).get(2);
		ArrayList<Integer> items_quantity = oudao.getAllOutgoingOrderInformationInTheSystem().get(i).get(3);
		for (int pi = 0; pi < products_ids.size(); pi++) {
			if (items_quantity.get(pi) != 0) {
				System.out.println("    " + getProductById(products_ids.get(pi), productsInTheSystem).getProduct_name()
						+ "           " + items_quantity.get(pi) + "           "
						+ items_quantity.get(pi)
								* sudao.getPriceOfPoduct(products_ids.get(pi), suppliersInTheSystem, supplier_id)
						+ "€");
			}
		}
	}

	/**
	 * Choose product.
	 * 
	 * @param products given Product objects
	 * @param scanner  scanner
	 * @return the id of the selected product
	 */
	public int chooseProduct(ArrayList<Product> products, Scanner scanner) {

		ArrayList<Integer> products_ids = new ArrayList<Integer>();
		for (Product p : products) {
			products_ids.add(p.getProduct_id());
		}
		int product_id = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.print("Product: ");
				product_id = scanner.nextInt();
				scanner.nextLine();
				if (products_ids.contains(product_id)) {
					repeat = false;
				} else {
					throw new Exception("ERROR: you should enter an ID from the ones already on the screen.");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return product_id;
	}

	/**
	 * Enter items of the given product.
	 * 
	 * @param scanner                    scanner
	 * @param product_id                 given product's id
	 * @param stockOfProductsInTheSystem the stock of the products stored in memory
	 * @return the selected items of the given product
	 */
	public int chooseItemsForProduct(Scanner scanner, int product_id, ArrayList<Stock> stockOfProductsInTheSystem) {
		StockDAO sdao = new StockDAO();
		int product_stock = sdao.getStockByStockId(product_id, stockOfProductsInTheSystem).getStock_quantity(); // stock_id = product_id always
		int items = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.print("Items: ");
				items = scanner.nextInt();
				scanner.nextLine();
				if (items <= product_stock) {
					repeat = false;
				} else {
					throw new Exception(
							"ERROR: you should enter maximum the number of items in the stock. Stock of product (ID="
									+ product_id + ") -->" + product_stock);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return items;
	}
}
