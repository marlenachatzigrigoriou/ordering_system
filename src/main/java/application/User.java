package application;

/**
 * Includes the information and data access functions about an user. A user can
 * have the role of salesman, warehouse staff or manager.
 * 
 * @author marlenachatzigrigoriou
 */
public abstract class User {

	/**
	 * The auto-generated id of the user.
	 */
	private int user_id;

	/**
	 * The full name of the user.
	 */
	private String full_name;

	/**
	 * The username of the user.
	 */
	private String username;

	/**
	 * The password of the user.
	 */
	private String password;

	/**
	 * The category of the user role.
	 */
	private String category;

	/**
	 * A static counter that helps in the auto-generation of user_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class.
	 * 
	 * @param full_name the full name of the user.
	 * @param username  the username of the user.
	 * @param password  the password of the user.
	 */
	public User(String full_name, String username, String password) {
		this.full_name = full_name;
		this.username = username;
		this.password = password;
		this.user_id = count++;
	}

	/**
	 * Getter function of the user id.
	 * 
	 * @return user's id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * Setter function of the user id.
	 * 
	 * @param user_id the auto-generated id of the user.
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * Getter function of the user full name.
	 * 
	 * @return user's full name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * Setter function of the user full name.
	 * 
	 * @param full_name the full name of the user.
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	/**
	 * Getter function of the user username.
	 * 
	 * @return user's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter function of the user username.
	 * 
	 * @param username the username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter function of the user password.
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter function of the user password.
	 * 
	 * @param user_id the password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter function of the user category.
	 * 
	 * @return user's category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter function of the user category.
	 * 
	 * @param user_id the category of the user.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Setter function of the counter. Only needed for the correct
	 * implementation of the unit tests.
	 * 
	 * @param count the counter that helps in the auto-generation of oder_id.
	 */
	public static void setCount(int count) {
		User.count = count;
	}
	
	/**
	 * Defines the equal function between two objects of the User class, 
	 * and check if they are equal.
	 * 
	 * @param o an User object.
	 *
	 * @return a true or false value 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return full_name == user.full_name && username == user.username
				&& password == user.password;
	}

}
