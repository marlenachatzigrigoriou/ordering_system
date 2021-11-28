package applicationDAO;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import application.Item;
import application.Order;
import application.Product;
import application.Supplier;
import application.User;
import demo.DataGenerator;

public class ItemDAOTest {

	private static ArrayList<Item> itemsInTheSystem = new ArrayList<Item>();
	private static DataGenerator dg = new DataGenerator();
	private ItemDAO itdao = new ItemDAO();

	@BeforeClass
	public static void initInputs() {
		Order.setCount(0);
		Supplier.setCount(0);
		Product.setCount(0);
		User.setCount(0);
		itemsInTheSystem = dg.itemGenerator1();
	}

	@Test
	public void testGetItemById() {
		Item expected = itemsInTheSystem.get(0);
		Item actual = itdao.getItemById(0, itemsInTheSystem);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetItemByIdNull() {
		Item actual = itdao.getItemById(7, itemsInTheSystem);
		Assert.assertNull(actual);
	}

}
