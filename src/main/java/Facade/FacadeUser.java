package Facade;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import application.*;
import applicationDAO.*;

public class FacadeUser {

	IncomingOrderDAO iodao = new IncomingOrderDAO();
	OutgoingOrderDAO oudao = new OutgoingOrderDAO();

	public ArrayList<User> createNewUser(Scanner scanner, ArrayList<User> usersInTheSystem) {
		System.out.println("Enter user's full name:");
		String name = scanner.nextLine();
		String dep = chooseDep(scanner);
		System.out.println("Enter user's username:");
		String username = scanner.nextLine();
		String password1 = enterValidPassword(scanner, "Enter user's password:");
		String password2 = enterValidPassword(scanner, "Enter again user's password:");
		if (password1.equals(password2)) {
			UserFactory ufac = new UserFactory();
			usersInTheSystem.add(ufac.createUser(name, username, password1, dep));
		}
		return usersInTheSystem;
	}

	public User login(Scanner scanner, ArrayList<User> usersInTheSystem) {
		return UserDAO.login(scanner, usersInTheSystem);
	}

	public ArrayList<Integer> listOrdersFromSalemanUser(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> incomingOrdersInTheSystem, User user_obj) {
		return iodao.listOrdersFromSalemanUser(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
				incomingOrdersInTheSystem, user_obj);

	}

	public void listOrdersFromWarehouseUser(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem, User user_obj) {
		oudao.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
				outgoingOrdersInTheSystem, user_obj);
	}

	public String enterValidPassword(Scanner scanner, String message) {
		String subba = null;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println(message);
				subba = scanner.nextLine();
				Pattern p = Pattern.compile("[^a-zA-Z0-9]");
				Matcher m = p.matcher(subba);
				boolean b = m.find();
				if (b && subba.length() >= 8) {
					repeat = false;
					return subba;
				} else {
					throw new Exception("ERROR: enter at least 8 characters (1 capital letter and 1 special symbol).");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return subba;
	}
	
	public String chooseDep(Scanner scanner) {
		String subba = null;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println("Enter user's department:");
				subba = scanner.nextLine();
				if (subba.equals("Salesman") || subba.equals("Warehouse") || subba.equals("Manager")) {
					repeat = false;
					return subba;
				} else {
					throw new Exception("ERROR: you should enter \"Salesman\" or \"Warehouse\" or \"Manager\"");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				repeat = true;
			}
		}
		return subba;
	}
}