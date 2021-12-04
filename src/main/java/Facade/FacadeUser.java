package facade;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.*;
import applicationDAO.*;

/**
 * The FacadeUser class contains the options displayed to system's users.
 * 
 * @author marlenachatzigrigoriou
 */
public class FacadeUser {

	/**
	 * IncomingOrderDAO object.
	 */
	IncomingOrderDAO iodao = new IncomingOrderDAO();

	/**
	 * OutgoingOrderDAO object.
	 */
	OutgoingOrderDAO oudao = new OutgoingOrderDAO();

	/**
	 * Create a new user and stores him in memory.
	 * 
	 * @param scanner          scanner
	 * @param usersInTheSystem the stored in the memory users
	 * @return the list of users stored in memory
	 */
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

	/**
	 * Log the user in the system.
	 * 
	 * @param usersInTheSystem the stored in the memory users
	 * @return the User object
	 */
	public User login(ArrayList<User> usersInTheSystem, Scanner scanner) {
		return UserDAO.login(usersInTheSystem, scanner);
	}

	/**
	 * Calls the method that prints all the orders that were submitted by the given
	 * salesman.
	 * 
	 * @param productsInTheSystem       the stored in the memory products
	 * @param usersInTheSystem          the stored in the memory users
	 * @param shopsInTheSystem          the stored in the memory shops
	 * @param incomingOrdersInTheSystem all the incoming orders stored in the memory
	 * @param user_obj                  User object
	 * @return the order ids of the orders submitted by the given salesman
	 */
	public ArrayList<Integer> listOrdersFromSalemanUser(ArrayList<Product> productsInTheSystem,
			ArrayList<User> usersInTheSystem, ArrayList<Shop> shopsInTheSystem,
			ArrayList<Order> incomingOrdersInTheSystem, User user_obj) {
		return iodao.listOrdersFromSalemanUser(productsInTheSystem, usersInTheSystem, shopsInTheSystem,
				incomingOrdersInTheSystem, user_obj);

	}

	/**
	 * Prints a list of the outgoing orders submitted by the given warehouse user.
	 * 
	 * @param productsInTheSystem       the stored in the memory products
	 * @param usersInTheSystem          the stored in the memory users
	 * @param suppliersInTheSystem      the stored in the memory suppliers
	 * @param outgoingOrdersInTheSystem all the outgoing orders stored in the memory
	 * @param user_obj                  User object
	 */
	public void listOrdersFromWarehouseUser(ArrayList<Product> productsInTheSystem, ArrayList<User> usersInTheSystem,
			ArrayList<Supplier> suppliersInTheSystem, ArrayList<Order> outgoingOrdersInTheSystem, User user_obj) {
		oudao.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
				outgoingOrdersInTheSystem, user_obj);
	}

	/**
	 * Check and insert valid password.
	 * 
	 * @param scanner scanner
	 * @param message message displayed to re-enter input
	 * @return valid input
	 */
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

	/**
	 * Check and insert valid user's department input.
	 * 
	 * @param scanner scanner
	 * @return valid input
	 */
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