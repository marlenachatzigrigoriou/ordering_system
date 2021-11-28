package applicationDAO;

import java.util.ArrayList;
import application.Salesman;
import application.User;

public class SalesmanDAO extends UserDAO {

	@Override
	public User getUserByUserId(int user_id, ArrayList<User> usersInTheSystem) {

		for (User user : usersInTheSystem) {
			if (user.getCategory().equals("Salesman")) {
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
			if (u.getCategory().equals("Salesman")) {
				Salesman sm = (Salesman) u;
				System.out.println("Name: " + sm.getFull_name() + ", Department: " + sm.getCategory());
			}
		}
	}

}
