package applicationDAO;

import java.util.ArrayList;

import application.Manager;
import application.User;

public class ManagerDAO extends UserDAO {

	@Override
	public User getUserByUserId(int user_id, ArrayList<User> usersInTheSystem) {
		for (User user : usersInTheSystem) {
			if (user.getCategory().equals("Manager")) {
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
			if (u.getCategory().equals("Manager")) {
				Manager sm = (Manager) u;
				System.out.println("Name: " + sm.getFull_name() + ", Department: " + sm.getCategory());
			}
		}
	}

}
