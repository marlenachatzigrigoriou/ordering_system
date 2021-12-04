package applicationDAO;

import java.util.ArrayList;

import application.User;
import application.Warehouse;
/**
 * Warehouse DAO class that contains the process of the Warehouse
 * objects.
 * 
 * @author marlenachatzigrigoriou
 */
public class WarehouseDAO extends UserDAO {

	@Override
	public User getUserByUserId(int user_id, ArrayList<User> usersInTheSystem) {
		for (User user : usersInTheSystem) {
			if (user.getCategory().equals("Warehouse")) {
				if (user.getUser_id() == user_id) {
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public void listUsers(ArrayList<User> usersInTheSystem) {
		for (User u : usersInTheSystem) {
			if (u.getCategory().equals("Warehouse")) {
				Warehouse sm = (Warehouse) u;
				System.out.println("Name: " + sm.getFull_name() + ", Department: " + sm.getCategory());
			}
		}
	}

}
