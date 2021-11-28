package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Salesman;
import application.User;

public abstract class UserDAO {

	public abstract User getUserByUserId(int user_id, ArrayList<User> usersInTheSystem);

	public abstract void listUsers(ArrayList<User> usersInTheSystem);

	public void viewUser(User user) {
		System.out.println(user.getFull_name());
	}

	public static User authenticate(String username, String password, ArrayList<User> usersInTheSystem) {
		for (User user : usersInTheSystem) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		System.err.println("Wrong username or password.");
		User user = new Salesman("none", "none", "none");
		return user;
	}

	public static User login(Scanner scanner, ArrayList<User> usersInTheSystem) {
		boolean false_credentials = true;
		User user_obj = null;
		while (false_credentials) {
			System.out.println("Username:");
			String username = scanner.nextLine();
			System.out.println("Password:");
			String password = scanner.nextLine();
			user_obj = authenticate(username, password, usersInTheSystem);
			if (user_obj.getFull_name().equals("none")) {
				System.out.println("Try again.");
			} else {
				false_credentials = false;
			}
		}
		System.out.println("Welcome, " + user_obj.getFull_name() + "!");
		return user_obj;
	}

}
