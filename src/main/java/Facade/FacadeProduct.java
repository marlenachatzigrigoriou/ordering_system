package Facade;

import java.util.ArrayList;
import java.util.Scanner;

import application.Order;
import application.Product;
import application.Stock;
import application.Supplier;
import application.User;
import applicationDAO.ProductDAO;
import applicationDAO.StockDAO;

public class FacadeProduct {
	
	public void listProducts(ArrayList<Product> productsInTheSystem) {
        for (Product product : productsInTheSystem){
            System.out.println(product.getProduct_id() + " | " + product.getProduct_name() + " , " + product.getProduct_size() 
                                + " inches , " + product.getProduct_weight() + " kg , " + product.getProduct_metal() + " , " + product.getProduct_price() + "€ ");
        }
    }
	
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
			int product_id = pdao.chooseProduct(productsInTheSystem, scanner); //only the existent ids
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