package application;

public class Warehouse extends User {

	public Warehouse(String full_name, String username, String password) {
		super(full_name, username, password);
		this.setCategory("Warehouse");
	}

}
