package application;

public class Manager extends User {

	public Manager(String full_name, String username, String password) {
		super(full_name, username, password);
		this.setCategory("Manager");

	}

}