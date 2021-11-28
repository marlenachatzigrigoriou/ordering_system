package demo;

import java.util.ArrayList;
import java.util.Scanner;

import Facade.FacadeDemo;
import Facade.FacadeUser;
import application.*;


public class DemoClient {

	public static void main(String[] args) {

		DataGenerator dg = new DataGenerator();

		ArrayList<User> usersInTheSystem = dg.usersGenerator();
		ArrayList<Shop> shopsInTheSystem = dg.shopsGenerator();
		ArrayList<Product> productsInTheSystem = dg.productsGenerator();
		ArrayList<Stock> stockOfProductsInTheSystem = dg.stockGenerator(productsInTheSystem);
		ArrayList<Order> incomingOrdersInTheSystem = dg.incomingOrdersGenerator();
		ArrayList<Supplier> suppliersInTheSystem = dg.supplierGenerator();
		ArrayList<Order> outgoingOrdersInTheSystem = dg.outgoingOrdersGenerator();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Do you want to see the data of the system?\nYes/No");
		String answer = scanner.nextLine();
		if (!(answer.equals("No") || answer.equals("no"))) {
			FacadeDemo fd = new FacadeDemo();
			fd.printGeneratedData(usersInTheSystem, shopsInTheSystem, incomingOrdersInTheSystem, productsInTheSystem,
					stockOfProductsInTheSystem, suppliersInTheSystem, outgoingOrdersInTheSystem);
		}

		boolean next_user = true;
		String action = "stay";

		while (next_user) {

			System.out.println("To enter as a user: 1 \nTo Exit: 0");
			String exit = scanner.nextLine();

			if (!exit.equals("0")) {
				System.out.println("Login");
				FacadeUser fu = new FacadeUser();
				User user_obj = fu.login(scanner, usersInTheSystem);
				String user_category = user_obj.getCategory();

				if (user_category.equals("Salesman") && !action.equals("logout")) {

					FacadeDemo fd = new FacadeDemo();
					fd.salesmanInterface(user_obj, scanner, productsInTheSystem, usersInTheSystem, shopsInTheSystem,
							incomingOrdersInTheSystem, suppliersInTheSystem, stockOfProductsInTheSystem);
					action = "logout";

				} else if (user_category.equals("Warehouse") && !action.equals("logout")) {
					FacadeDemo fd = new FacadeDemo();
					fd.warehouseInterface(user_obj, scanner, productsInTheSystem, usersInTheSystem, shopsInTheSystem,
							outgoingOrdersInTheSystem, incomingOrdersInTheSystem, suppliersInTheSystem,
							stockOfProductsInTheSystem);
					action = "logout";

				} else if (user_category.equals("Manager") && !action.equals("logout")) { // manager has the bigger
																							// control, he can do
																							// anything
					FacadeDemo fd = new FacadeDemo();
					fd.managerInterface(user_obj, scanner, productsInTheSystem, usersInTheSystem, shopsInTheSystem,
							outgoingOrdersInTheSystem, incomingOrdersInTheSystem, suppliersInTheSystem,
							stockOfProductsInTheSystem);
					action = "logout";
				}

			} else {
				next_user = false;
			}
			action = "stay";
		}
		scanner.close();

	}
}