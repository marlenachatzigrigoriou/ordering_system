package application;

/**
 * The factory of User class, that generates User objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class UserFactory {

	/**
	 * Object of class User.
	 */
	private User user;

	/**
	 * Generates User objects, depending on the defined user category.
	 * 
	 * @param full_name the full name of the user.
	 * @param username  the username of the user.
	 * @param password  the password of the user.
	 * @param category  the category of the user.
	 * @return user the created user object
	 */
	public User createUser(String full_name, String username, String password, String category) {
		if (category.equals("Salesman")) {
			user = new Salesman(full_name, username, password);
		} else if (category.equals("Warehouse")) {
			user = new Warehouse(full_name, username, password);
		} else if (category.equals("Manager")) {
			user = new Manager(full_name, username, password);
		}
		return user;
	}
}