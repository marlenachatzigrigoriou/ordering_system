package application;

public class UserFactory {
	/**
	 * 
	 *
	 */
	private User user;

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