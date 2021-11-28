package application;

public class Shop {

	private int shop_id;
	private String shop_name;
	private String shop_address;
	private String shop_manager;
	private int shop_industry_code;
	private static int count = 0;

	public Shop(String shop_name, String shop_address, String shop_manager, int shop_industry_code) {
		this.shop_id = count++; // auto-generated id
		this.shop_name = shop_name;
		this.shop_address = shop_address;
		this.shop_manager = shop_manager;
		this.shop_industry_code = shop_industry_code;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShop_manager() {
		return shop_manager;
	}

	public void setShop_manager(String shop_manager) {
		this.shop_manager = shop_manager;
	}

	public int getShop_industry_code() {
		return shop_industry_code;
	}

	public void setShop_industry_code(int shop_industry_code) {
		this.shop_industry_code = shop_industry_code;
	}

}