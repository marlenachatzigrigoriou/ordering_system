package application;

/**
 * Salesman class that inherits from the User one.
 * 
 * @author marlenachatzigrigoriou
 */
public class Salesman extends User {

	/**
	 * Constructor class. Inherits from the User class.
	 * 
	 * @param full_name the full name of the user.
	 * @param username  the username of the user.
	 * @param password  the password of the user.
	 */
	public Salesman(String full_name, String username, String password) {
		super(full_name, username, password);
		this.setCategory("Salesman");
	}

}
