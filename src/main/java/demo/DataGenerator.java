package demo;

import java.util.ArrayList;
import application.*;
import applicationDAO.*;

public class DataGenerator {

	SalesmanDAO sdao = new SalesmanDAO();
	WarehouseDAO wdao = new WarehouseDAO();
	ShopDAO shdao = new ShopDAO();
	ProductDAO pdao = new ProductDAO();
	IncomingOrderDAO iodao = new IncomingOrderDAO();
	ItemDAO itdao = new ItemDAO();
	StockDAO stdao = new StockDAO();
	SupplierDAO sudao = new SupplierDAO();
	OutgoingOrderDAO oudao = new OutgoingOrderDAO();
	ManagerDAO mdao = new ManagerDAO();

	// almost facade
	public ArrayList<User> usersGenerator() {
		String fullnames[] = { "Lars Luxin", "Emily Bori", "Vasilis Salvan", "Pablo Ntakis", "Jakob Chilo",
				"Fillipo Kreft", "Nicolo Ceni", "Mathiew Tjihssen", "Alex Chonas" };
		String usernames[] = { "lalu21", "embo20", "vasa21", "pant23", "jach23", "fikre22", "nice19", "matj24",
				"alch22" };
		String passwords[] = { "swde7$8451RT4Hho", "5d845dT85#YhiY", "yy8Ae!5g6Top", "divf8T7!h569R", "asw85S!k8O0w",
				"c8vf5frRR47f!d8", "t7v8s5edfgvkT5G!", "u2bj#f88p85O5Pdx", "c8Y6N4s!ik8hA" };
		String categories[] = { "Salesman", "Salesman", "Salesman", "Salesman", "Warehouse", "Warehouse", "Warehouse",
				"Warehouse", "Manager" };
		ArrayList<User> usersInTheSystem = new ArrayList<User>();
		UserFactory userfactory = new UserFactory();
		for (int i = 0; i < fullnames.length; i++) {
			usersInTheSystem.add(userfactory.createUser(fullnames[i], usernames[i], passwords[i], categories[i]));
		}
		return usersInTheSystem;
	}

	private ArrayList<Shop> shopsInTheSystem = new ArrayList<Shop>();

	public ArrayList<Shop> shopsGenerator() {
		Shop s1 = new Shop("Media Markt", "Skibhusvej 78, 5000 Odense", "Frederik Dolriis", 148);
		shdao.storeShopsInMemory(shopsInTheSystem, s1);
		Shop s2 = new Shop("BAUHAUS", "Risingsvej 69, 5000 Odense C", "Ole Jensen", 148);
		shdao.storeShopsInMemory(shopsInTheSystem, s2);
		Shop s3 = new Shop("JYSK", "Risingsvej 67, 5000 Odense C", "Kristian Rasmussen ", 150);
		shdao.storeShopsInMemory(shopsInTheSystem, s3);
		Shop s4 = new Shop("IKEA", "Ørbækvej 91, 5220 Odense", "Jakob Iversen", 150);
		shdao.storeShopsInMemory(shopsInTheSystem, s4);
		return shopsInTheSystem;
	}

	private ArrayList<Supplier> suppliersInTheSystem = new ArrayList<Supplier>();

	public ArrayList<Supplier> supplierGenerator() {
		// supplier A
		ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> a1 = new ArrayList<Double>();
		a1.add(0.0); // product_id
		a1.add(20.0); // price of product_id
		productIdsAndPricesAvailableToSupply.add(a1);
		ArrayList<Double> a2 = new ArrayList<Double>();
		a2.add(1.0);
		a2.add(25.0);
		productIdsAndPricesAvailableToSupply.add(a2);
		ArrayList<Double> a3 = new ArrayList<Double>();
		a3.add(2.0);
		a3.add(25.0);
		productIdsAndPricesAvailableToSupply.add(a3);
		Supplier s1 = new SupplierA("A", "Dannebrogsgade 24, 5000 Odense", productIdsAndPricesAvailableToSupply);
		sudao.storeSuppliersInMemory(suppliersInTheSystem, s1);
		// supplier B
		ArrayList<ArrayList<Double>> productIdsAndPricesAvailableToSupply1 = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> a4 = new ArrayList<Double>();
		a4.add(3.0);
		a4.add(30.0);
		productIdsAndPricesAvailableToSupply1.add(a4);
		Supplier s2 = new SupplierB("B", "H.C. Andersen Haven 1, 5000 Odense C", productIdsAndPricesAvailableToSupply1);
		sudao.storeSuppliersInMemory(suppliersInTheSystem, s2);
		return suppliersInTheSystem;
	}

	private ArrayList<Product> productsInTheSystem = new ArrayList<Product>();

	public ArrayList<Product> productsGenerator() {
		String names[] = { "Silver Pan 893", "Silver Pro Pan 999", "Silver Pot 833", "Silver Pot 888" };
		double prices[] = { 40.00, 50.00, 50.00, 60.00 };
		double sizes[] = { 120.00, 130.00, 120.00, 130.00 };
		double weights[] = { 1.00, 0.90, 1.50, 1.20 };
		String metals[] = { "Aluminum", "Cast Iron", "Aluminum", "Cast Iron" };
		String categories[] = { "Pan", "Pan", "Pot", "Pot" };
		ProductFactory pfactory = new ProductFactory();
		for (int i = 0; i < names.length; i++) {
			productsInTheSystem
					.add(pfactory.createProduct(names[i], prices[i], sizes[i], weights[i], metals[i], categories[i]));
		}
		return productsInTheSystem;
	}

	private ArrayList<Stock> stockOfProductsInTheSystem = new ArrayList<Stock>();

	public ArrayList<Stock> stockGenerator(ArrayList<Product> productsInTheSystem) {
		int stock = 100;
		for (Product product : productsInTheSystem) {
			Stock s = new Stock(product.getProduct_id(), stock, 100);
			stdao.storeStockOfProductsInMemory(stockOfProductsInTheSystem, s);
			stock -= 25;
		}
		return stockOfProductsInTheSystem;
	}

	public ArrayList<Item> itemGenerator1() {
		ArrayList<Item> itemsInTheSystem = new ArrayList<Item>();
		Item i1 = new Item(5);
		itdao.storeItemsInMemory(itemsInTheSystem, i1);
		Item i2 = new Item(10);
		itdao.storeItemsInMemory(itemsInTheSystem, i2);
		Item i3 = new Item(2);
		itdao.storeItemsInMemory(itemsInTheSystem, i3);
		return itemsInTheSystem;
	}

	public ArrayList<Item> itemGenerator2() {
		ArrayList<Item> itemsInTheSystem = new ArrayList<Item>();
		Item i4 = new Item(8);
		itdao.storeItemsInMemory(itemsInTheSystem, i4);
		return itemsInTheSystem;
	}

	private ArrayList<Order> incomingOrdersInTheSystem = new ArrayList<Order>();

	public ArrayList<Order> incomingOrdersGenerator() {
		// order1
		ArrayList<Item> itemsInTheSystem = itemGenerator1();
		int[][] products_items1 = new int[3][2];
		products_items1[0][0] = productsInTheSystem.get(0).getProduct_id();
		products_items1[0][1] = itemsInTheSystem.get(0).getQuantity();
		products_items1[1][0] = productsInTheSystem.get(2).getProduct_id();
		products_items1[1][1] = itemsInTheSystem.get(1).getQuantity();
		products_items1[2][0] = productsInTheSystem.get(1).getProduct_id();
		products_items1[2][1] = itemsInTheSystem.get(2).getQuantity();
		iodao.createNewIncomingOrder(0.1, 1, 2, products_items1, productsInTheSystem, incomingOrdersInTheSystem,
				stockOfProductsInTheSystem);
		itemsInTheSystem.clear();// i need items list for each order! after an order, i clear the list
		// order2
		ArrayList<Item> itemsInTheSystem2 = itemGenerator2();
		itemsInTheSystem2 = itemGenerator2();
		int[][] products_items2 = new int[1][2];
		products_items2[0][0] = productsInTheSystem.get(3).getProduct_id();
		products_items2[0][1] = itemsInTheSystem2.get(0).getQuantity();
		iodao.createNewIncomingOrder(0.0, 3, 2, products_items2, productsInTheSystem, incomingOrdersInTheSystem,
				stockOfProductsInTheSystem);
		itemsInTheSystem2.clear();// i need items list for each order! after an order, i clear the list
		return incomingOrdersInTheSystem;
	}

	private ArrayList<Order> outgoingOrdersInTheSystem = new ArrayList<Order>();

	public ArrayList<Order> outgoingOrdersGenerator() {// order made by warehouse
		// order1
		int[][] products_items1 = new int[2][2];
		products_items1[0][0] = 0;
		products_items1[0][1] = 50;
		products_items1[1][0] = 1;
		products_items1[1][1] = 30;
		oudao.createNewOutgoingOrder(5, 0, products_items1, productsInTheSystem, outgoingOrdersInTheSystem,
				suppliersInTheSystem);
		// order2
		int[][] products_items2 = new int[1][2];
		products_items2[0][0] = 3;
		products_items2[0][1] = 20;
		oudao.createNewOutgoingOrder(4, 1, products_items2, productsInTheSystem, outgoingOrdersInTheSystem,
				suppliersInTheSystem);
		return outgoingOrdersInTheSystem;
	}

}
