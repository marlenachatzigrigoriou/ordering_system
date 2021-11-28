package application;

public class Salesman extends User {

	public Salesman(String full_name, String username, String password) {
		super(full_name, username, password);
		this.setCategory("Salesman");
	}

}
