package applicationDAO;

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

import application.Order;
import application.OrderFactory;
import application.OutgoingOrder;
import application.Product;
import application.Stock;
import application.Supplier;
import application.User;
import application.Warehouse;

public class OutgoingOrderDAO extends OrderDAO {
	
	// (outgoing_order, [[warehouse_id], [supplier_id], [products_ids], [items_ids]])
    private static HashMap<OutgoingOrder, ArrayList<ArrayList<Integer>>> allOutgoingOrderInformationInTheSystem =  new HashMap<OutgoingOrder, ArrayList<ArrayList<Integer>>>();

    public HashMap<OutgoingOrder, ArrayList<ArrayList<Integer>>> getAllOutgoingOrderInformationInTheSystem() {
        return allOutgoingOrderInformationInTheSystem;
    }

    @Override
    public ArrayList<Order> storeOrdersInMemory(ArrayList<Order> outgoingOrdersInTheSystem, Order o) {
    	outgoingOrdersInTheSystem.add(o);
    	return outgoingOrdersInTheSystem;
    }

    
	@Override
	public ArrayList<Integer> getOrdersByUserId(int user_id) {
    	ArrayList<Integer> orders_ids = new ArrayList<Integer>();
    	for (OutgoingOrder order : allOutgoingOrderInformationInTheSystem.keySet()) {
    		int userid = allOutgoingOrderInformationInTheSystem.get(order).get(0).get(0);
    		if (user_id == userid) {
    			orders_ids.add(order.getOrder_id());
    		}
    	}
    	return orders_ids;		
	}
    
	
	@Override
    public void storeAllOrderInformationInMemory(Order order, ArrayList<Integer> warehouse_id, ArrayList<Integer> supplier_id, 
            														                         ArrayList<Integer> products_ids, ArrayList<Integer> items_ids) {
		ArrayList<ArrayList<Integer>> order_info = new ArrayList<ArrayList<Integer>>();
		order_info.add(warehouse_id);
		order_info.add(supplier_id);
		order_info.add(products_ids);
		order_info.add(items_ids);
		allOutgoingOrderInformationInTheSystem.put((OutgoingOrder) order, order_info);        
	}
	
	
    public ArrayList<Integer> getOrdersBySupplierId(int supplier_id) {
    	ArrayList<Integer> orders_ids = new ArrayList<Integer>();
    	for (OutgoingOrder order : allOutgoingOrderInformationInTheSystem.keySet()) {
    		int supplierid = allOutgoingOrderInformationInTheSystem.get(order).get(1).get(0);
    		if (supplier_id == supplierid) {
    			orders_ids.add(order.getOrder_id());
    		}
    	}
    	return orders_ids;
    }
    
    
    public OutgoingOrder createNewOutgoingOrder(int warehouse_id, int supplier_id, int[][] products_items, ArrayList<Product> productsInTheSystem, 
    																	ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Supplier> suppliersInTheSystem) {
    	OutgoingOrder order = submitOutgoingOrder(products_items, supplier_id, productsInTheSystem, outgoingOrdersInTheSystem, suppliersInTheSystem);
        // Transform the order's info into the correct data type to enter the hashMap
    	ArrayList<Integer> products_ids = new ArrayList<>(), items_ids = new ArrayList<>(), warehouse = new ArrayList<>(), supplier = new ArrayList<>();
        for (int[] k : products_items){ 
            products_ids.add(k[0]); 
            items_ids.add(k[1]); 
        }        
        warehouse.add(warehouse_id);
        supplier.add(supplier_id);       
        storeAllOrderInformationInMemory(order, warehouse, supplier, products_ids, items_ids);
        return order;
        // i will not increase stock now! --> the stock will be increased only when the products will arrive physically in the warehouse
    }
    
    //whatever was usergenerator for its factory in generator class
    public OutgoingOrder submitOutgoingOrder(int[][] products_items, int supplier_id, ArrayList<Product> productsInTheSystem,
    																	ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Supplier> suppliersInTheSystem){
    	double total_cost = calculateOutgoingOrderTotalCost(products_items, supplier_id, productsInTheSystem, suppliersInTheSystem);
		Date date = Calendar.getInstance().getTime();
		OrderFactory ofac = new OrderFactory();
		OutgoingOrder outo = (OutgoingOrder) ofac.createOrder(total_cost, date, 0.0, "Outgoing");
//		OutgoingOrder outo = new OutgoingOrder(total_cost, date, 0.0); //the final total_cost (calculated with the discount) is generated here!
		storeOrdersInMemory(outgoingOrdersInTheSystem, outo);
		return outo;
	}
    
    public double calculateOutgoingOrderTotalCost (int products_items[][], int supplier_id, ArrayList<Product> productsInTheSystem, 
    																										ArrayList<Supplier> suppliersInTheSystem) {
        // products_items = N x 2
        //
        //           product_id  |  item_quantity
        //           _____________________________
        //          |     1              5          --> product+item
        //          |   
        //          |     2              10         --> product+item
        //
        //   ex.: products_items[0,0] & products_items[1,0] --> buy 20 items of product 1 --> 20 * (price of 1) = cost
    	double total_cost = 0.00; 
		SupplierDAO sudao = new SupplierDAO();
    	for (int i = 0; i < products_items.length; i++){ // for all the selected (by the salesman) products of that specific order
            int product_id = products_items[i][0];
 			double price = sudao.getPriceOfPoduct(product_id, suppliersInTheSystem, supplier_id);
 			total_cost += price * products_items[i][1];
        }
    	return total_cost;
    }
    
    public void automatedOutgoingOrder(int warehouse_id, int product_id, ArrayList<Stock> stockOfProductsInTheSystem, ArrayList<Product> productsInTheSystem, 
																ArrayList<Order> outgoingOrdersInTheSystem, ArrayList<Supplier> suppliersInTheSystem) {
    	int stock_id = product_id;
    	StockDAO stdao = new StockDAO();
    	Stock s = stdao.getStockByStockId(stock_id, stockOfProductsInTheSystem);
//    	System.out.println(s);
    	int items_to_order = s.getStandard_quantity_request();
    	
        int[][] products_items2 = new int[1][2];  //each automated order
        products_items2[0][0] = product_id;       //is only for 
        products_items2[0][1] = items_to_order;   //one supplier and one product
        SupplierDAO sudao = new SupplierDAO();
        int supplier_id = sudao.getSupplierIdOfProduct(product_id, suppliersInTheSystem);
        
        OutgoingOrder order = createNewOutgoingOrder(warehouse_id, supplier_id, products_items2,  productsInTheSystem, outgoingOrdersInTheSystem, suppliersInTheSystem);    	
        System.out.println("\nAn automated outgoing order just created (since shop's order got product's stock less than the lower stock limit).\nAUTOMATED ORDER SUMMARY:\n");
        ArrayList<User> user_is_system = new ArrayList<User>();
        user_is_system.add(new Warehouse("system", "system", "system"));
        user_is_system.get(0).setUser_id(100000); // if user_id == 100000 --> user = system
        viewOutgoingOrderInformation(order, productsInTheSystem, user_is_system, suppliersInTheSystem);
    }
    
    public void listOrdersFromWarehouseUser(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem, 
											ArrayList<Supplier> suppliersInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem, User user_obj) {
		int warehouse_id = user_obj.getUser_id();
		String warehouse_name = user_obj.getFull_name();
		System.out.println("Orders submitted by me: " + warehouse_name + "  " + warehouse_id);
		ArrayList<Integer> orders_ids = getOrdersByUserId(warehouse_id);
		if (orders_ids.size() == 0) { 
			System.out.println("None yet");
		} else {
			for (int i : orders_ids) {
				Order order  = getOrderByOrderId(i, outgoingOrdersInTheSystem);
				viewOutgoingOrderInformation((OutgoingOrder) order, productsInTheSystem, usersInTheSystem, suppliersInTheSystem);
			}
		}
	}
    

    public void viewOutgoingOrderInformation(OutgoingOrder i, ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem, ArrayList<Supplier> suppliersInTheSystem) {
    	viewOrder(i);
		int supplier_id = getAllOutgoingOrderInformationInTheSystem().get(i).get(1).get(0);
		SupplierDAO sudao = new SupplierDAO();
		ProductDAO pdao = new ProductDAO();
		System.out.println("     Products           Items           Cost");
		pdao.printProductsOfOutgoingOrder(productsInTheSystem, suppliersInTheSystem, supplier_id, i);
		
		System.out.print("Responsible warehouse employee: ");
		int warehouse_id = getAllOutgoingOrderInformationInTheSystem().get(i).get(0).get(0);
		if (usersInTheSystem.get(0).getUser_id() == 100000) {
			System.out.println("Order created by the system!");
		} else {
			WarehouseDAO wdao = new WarehouseDAO();
			wdao.viewUser(wdao.getUserByUserId(warehouse_id, usersInTheSystem));
		}
		System.out.print("Purchase to supplier: ");
		sudao.viewSupplier(sudao.getSupplierById(supplier_id, suppliersInTheSystem));
	}
    
    

    
    
    public static void showOutgoingOrderSummary(int[][] products_items,int supplier_id, ArrayList<Product> productsInTheSystem, ArrayList<Supplier> suppliersInTheSystem){

		ProductDAO pdao = new ProductDAO();
        double total_cost = 0.0;
        System.out.println("     Products           Items");
        for (int pi = 0; pi < products_items.length; pi++){
            if (products_items[pi][1] != 0){ 
                System.out.println("    " + pdao.getProductById(products_items[pi][0], productsInTheSystem).getProduct_name() + "         " + products_items[pi][1]);
                total_cost = total_cost + pdao.getProductById(products_items[pi][0], productsInTheSystem).getProduct_price()*products_items[pi][1];
            }
        }
        System.out.println("Total cost: " + total_cost + "€");
        System.out.print("Purchase to: ");
		SupplierDAO sudao = new SupplierDAO();
        sudao.viewSupplier(sudao.getSupplierById(supplier_id, suppliersInTheSystem));
        System.out.println();
    }
    
}

