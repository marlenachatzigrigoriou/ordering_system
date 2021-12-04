package applicationDAO;

import java.util.ArrayList;
import java.util.Scanner;

import application.Salesman;
import application.User;

/**
 * User DAO class that contains the process of the User
 * objects.
 * 
 * @author marlenachatzigrigoriou
 */
public abstract class UserDAO {

	/**
	 * Returns the user to which the user id is assigned to.
	 * 
	 * @param user_id          the user's id
	 * @param usersInTheSystem the stored in the memory users
	 * @return the user
	 */
	public abstract User getUserByUserId(int user_id, ArrayList<User> usersInTheSystem);

	/**
	 * Lists the users that are stored in the system.
	 * 
	 * @param usersInTheSystem the stored in the memory users
	 */
	public abstract void listUsers(ArrayList<User> usersInTheSystem);

	/**
	 * Print user's full name.
	 * 
	 * @param user User object
	 */
	public void viewUser(User user) {
		System.out.println(user.getFull_name());
	}

	/**
	 * Authenticate the user's credentials.
	 * 
	 * @param username         user's username
	 * @param password         urer's password
	 * @param usersInTheSystem the stored in the memory users
	 * @return the authenticated user
	 */
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

	/**
	 * Login user in the system.
	 * 
	 * @param usersInTheSystem the stored in the memory users
	 * @return the loged in user object
	 */
	public static User login(ArrayList<User> usersInTheSystem) {
		Scanner scanner = new Scanner(System.in);
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
		scanner.close();
		System.out.println("Welcome, " + user_obj.getFull_name() + "!");
		return user_obj;
	}

}
