package applicationDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.contrib.java.lang.system.SystemOutRule;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import application.*;
import demo.DataGenerator;

public class OutgoingOrderDAOTest {

	private static ArrayList<Order> outgoingOrdersInTheSystem = new ArrayList<Order>();
	private static DataGenerator dg = new DataGenerator();
	private static OutgoingOrderDAO oudao = new OutgoingOrderDAO();
	private static ArrayList<Product> productsInTheSystem = new ArrayList<Product>();
	private static ArrayList<Supplier> suppliersInTheSystem = new ArrayList<Supplier>();
	private static ArrayList<User> usersInTheSystem = new ArrayList<User>();

	@BeforeClass
	public static void initInputs() {
		Order.setCount(0);
		Supplier.setCount(0);
		Product.setCount(0);
		User.setCount(0); 
		productsInTheSystem = dg.productsGenerator();
		suppliersInTheSystem = dg.supplierGenerator();
		usersInTheSystem = dg.usersGenerator();

		OrderFactory ofac = new OrderFactory();
		OutgoingOrder outo = (OutgoingOrder) ofac.createOrder(1750.00, Calendar.getInstance().getTime(), 0.0,
				"Outgoing");
		outgoingOrdersInTheSystem = oudao.storeOrdersInMemory(outgoingOrdersInTheSystem, outo);
		// order1
		int[][] products_items1 = new int[2][2];
		products_items1[0][0] = 0;
		products_items1[0][1] = 50;
		products_items1[1][0] = 1;
		products_items1[1][1] = 30;
		ArrayList<Integer> products_ids = new ArrayList<>(), items_ids = new ArrayList<>(),
				warehouse = new ArrayList<>(), supplier = new ArrayList<>();
		for (int[] k : products_items1) {
			products_ids.add(k[0]);
			items_ids.add(k[1]);
		}
		warehouse.add(5);
		supplier.add(0);
		oudao.storeAllOrderInformationInMemory(outo, warehouse, supplier, products_ids, items_ids);
	}

	@Test
	public void testStoreOrdersInMemory() {
		Order.setCount(1);
		List<Order> expected = new ArrayList<Order>();
		expected.add(outgoingOrdersInTheSystem.get(0));
		OrderFactory ofac = new OrderFactory();
		OutgoingOrder outo = (OutgoingOrder) ofac.createOrder(100.00, Calendar.getInstance().getTime(), 0.0,
				"Outgoing");
		expected.add(outo);
		List<Order> actuals = oudao.storeOrdersInMemory(outgoingOrdersInTheSystem, outo);
		Assert.assertEquals(expected, actuals);
		outgoingOrdersInTheSystem.remove(1); // so i can continue the rest of the tests with my known data
	}

	@Test
	public void testGetOrderByOrderId() {
		Order expected = outgoingOrdersInTheSystem.get(0);
		Order actual = oudao.getOrderByOrderId(0, outgoingOrdersInTheSystem);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetOrdersByUserId() {

		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		ArrayList<Integer> actual = oudao.getOrdersByUserId(5);
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testGetOrdersBySupplierId() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		ArrayList<Integer> actual = oudao.getOrdersBySupplierId(0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetOrdersBySupplierIdNotFound() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		ArrayList<Integer> actual = oudao.getOrdersBySupplierId(7);
		Assert.assertEquals(expected, actual);
	}

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testListOrdersFromWarehouseUserNone() {
		User user_obj = usersInTheSystem.get(4);
		oudao.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
				outgoingOrdersInTheSystem, user_obj);
		;
		Assert.assertEquals("Orders submitted by me: Jakob Chilo  4\r\n" + "None yet", systemOutRule.getLog().trim());

	}

	@Test
	public void testViewOutgoingOrderInformation() {
		oudao.viewOutgoingOrderInformation((OutgoingOrder) outgoingOrdersInTheSystem.get(0), productsInTheSystem,
				usersInTheSystem, suppliersInTheSystem);
		Date date = outgoingOrdersInTheSystem.get(0).getOrder_date(); //because the seconds are different when i call the Calendar method in the viewOutgoingOrderInformation 
																	// and when i call it in the assert
		Assert.assertEquals(
				"ID:  0 | Status: Pending, Date: " + date
						+ ", Total_cost: 1750.0€ (discount factored in total cost: 0.0)\r\n"
						+ "     Products           Items           Cost\r\n"
						+ "    Silver Pan 893           50           1000.0€\r\n"
						+ "    Silver Pro Pan 999           30           750.0€\r\n"
						+ "Responsible warehouse employee: Fillipo Kreft\r\n"
						+ "Purchase to supplier: ID: 0 | Name: A, Address: Dannebrogsgade 24, 5000 Odense",
				systemOutRule.getLog().trim());
	}

	@Test
	public void testListOrdersFromWarehouseUser() {
		User user_obj = usersInTheSystem.get(5);
		oudao.listOrdersFromWarehouseUser(productsInTheSystem, usersInTheSystem, suppliersInTheSystem,
				outgoingOrdersInTheSystem, user_obj);
		Date date = outgoingOrdersInTheSystem.get(0).getOrder_date();
		Assert.assertEquals("Orders submitted by me: Fillipo Kreft  5\r\n" + "ID:  0 | Status: Pending, Date: "
				+  date + ", Total_cost: 1750.0€ (discount factored in total cost: 0.0)\r\n"
				+ "     Products           Items           Cost\r\n"
				+ "    Silver Pan 893           50           1000.0€\r\n"
				+ "    Silver Pro Pan 999           30           750.0€\r\n"
				+ "Responsible warehouse employee: Fillipo Kreft\r\n"
				+ "Purchase to supplier: ID: 0 | Name: A, Address: Dannebrogsgade 24, 5000 Odense",
				systemOutRule.getLog().trim());
	}

	@Test
	public void testShowOutgoingOrderSummary() {

		int[][] products_items1 = new int[2][2];
		products_items1[0][0] = 0;
		products_items1[0][1] = 50;
		products_items1[1][0] = 1;
		products_items1[1][1] = 30;
		OutgoingOrderDAO.showOutgoingOrderSummary(products_items1, 0, productsInTheSystem, suppliersInTheSystem);

		Assert.assertEquals(
				"Products           Items\r\n" + "    Silver Pan 893         50\r\n"
						+ "    Silver Pro Pan 999         30\r\n" + "Total cost: 3500.0€\r\n"
						+ "Purchase to: ID: 0 | Name: A, Address: Dannebrogsgade 24, 5000 Odense",
				systemOutRule.getLog().trim());
	}

	@Test
	public void testShowOutgoingOrderSummaryZeroCase() {

		int[][] products_items1 = new int[2][2];
		products_items1[0][0] = 0;
		products_items1[0][1] = 0;
		products_items1[1][0] = 1;
		products_items1[1][1] = 30;
		OutgoingOrderDAO.showOutgoingOrderSummary(products_items1, 0, productsInTheSystem, suppliersInTheSystem);

		Assert.assertEquals(
				"Products           Items\r\n" + "    Silver Pro Pan 999         30\r\n" + "Total cost: 1500.0€\r\n"
						+ "Purchase to: ID: 0 | Name: A, Address: Dannebrogsgade 24, 5000 Odense",
				systemOutRule.getLog().trim());
	}

}