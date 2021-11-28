package application;

public abstract class User {

	private int user_id;
	private String full_name;
	private String username;
	private String password; // at least --> 8 chars: 1 capital letter, 2 numbers, 1 special symbol
	private static int count = 0;
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		User.count = count;
	}

	private String category;

	public User(String full_name, String username, String password) {
		this.full_name = full_name;
		this.username = username;
		this.password = password;
		this.user_id = count++;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
