package application;

/**
 * Includes the information and data access functions about a shop.
 * 
 * @author marlenachatzigrigoriou
 */
public class Shop {

	/**
	 * The auto-generated id of the shop.
	 */
	private int shop_id;

	/**
	 * The name of the shop.
	 */
	private String shop_name;

	/**
	 * The address of the shop.
	 */
	private String shop_address;

	/**
	 * The name of the manager of the shop.
	 */
	private String shop_manager;

	/**
	 * The industry code of the shop.
	 */
	private int shop_industry_code;

	/**
	 * A static counter that helps in the auto-generation of item_id.
	 */
	private static int count = 0;

	/**
	 * Constructor class.
	 * 
	 * @param shop_name          the shop's name.
	 * @param shop_address       the shop's address.
	 * @param shop_manager       the shop's manager.
	 * @param shop_industry_code the shop's industry code.
	 */
	public Shop(String shop_name, String shop_address, String shop_manager, int shop_industry_code) {
		this.shop_id = count++; // auto-generated id
		this.shop_name = shop_name;
		this.shop_address = shop_address;
		this.shop_manager = shop_manager;
		this.shop_industry_code = shop_industry_code;
	}

	/**
	 * Getter function of the shop id.
	 * 
	 * @return shop's id
	 */
	public int getShop_id() {
		return shop_id;
	}

	/**
	 * Setter function of the shop id.
	 * 
	 * @param shop_id the auto-generated id of the shop.
	 */
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	/**
	 * Getter function of the shop name.
	 * 
	 * @return shop's name
	 */
	public String getShop_name() {
		return shop_name;
	}

	/**
	 * Setter function of the shop name.
	 * 
	 * @param shop_name of the shop.
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	/**
	 * Getter function of the shop address.
	 * 
	 * @return shop's address
	 */
	public String getShop_address() {
		return shop_address;
	}

	/**
	 * Setter function of the shop address.
	 * 
	 * @param shop_address of the shop.
	 */
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	/**
	 * Getter function of the shop manager.
	 * 
	 * @return shop's manager
	 */
	public String getShop_manager() {
		return shop_manager;
	}

	/**
	 * Setter function of the shop manager.
	 * 
	 * @param shop_manager of the shop.
	 */
	public void setShop_manager(String shop_manager) {
		this.shop_manager = shop_manager;
	}

	/**
	 * Getter function of the shop industry code.
	 * 
	 * @return shop's industry code
	 */
	public int getShop_industry_code() {
		return shop_industry_code;
	}

	/**
	 * Setter function of the shop industry code.
	 * 
	 * @param shop_industry_code of the shop.
	 */
	public void setShop_industry_code(int shop_industry_code) {
		this.shop_industry_code = shop_industry_code;
	}

}