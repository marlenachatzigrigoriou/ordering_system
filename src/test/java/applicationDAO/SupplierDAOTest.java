package applicationDAO;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;

import application.*;
import demo.DataGenerator;

public class SupplierDAOTest {

	private SupplierDAO sudao = new SupplierDAO();
	private static DataGenerator dg = new DataGenerator();
	private static ArrayList<Product> productsInTheSystem = new ArrayList<Product>();
	private static ArrayList<Supplier> suppliersInTheSystem = new ArrayList<Supplier>();

	@BeforeClass
	public static void initInputs() {
		Order.setCount(0);
		Supplier.setCount(0);
		Product.setCount(0);
		User.setCount(0);
		productsInTheSystem = dg.productsGenerator();
		suppliersInTheSystem = dg.supplierGenerator();
	}

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testShowSuppliersAndTheirProducts() {
		sudao.showSuppliersAndTheirProducts(productsInTheSystem, suppliersInTheSystem);
		Assert.assertEquals("ID |   Product   | Supplier | Price \r\n" + "0 | Silver Pan 893 | A | 20.0€\r\n"
				+ "1 | Silver Pro Pan 999 | A | 25.0€\r\n" + "2 | Silver Pot 833 | A | 25.0€\r\n"
				+ "3 | Silver Pot 888 | B | 30.0€", systemOutRule.getLog().trim());
	}

	@Test
	public void test2ShowSuppliersAndTheirProducts() {
		productsInTheSystem.get(0).setProduct_id(14);
		sudao.showSuppliersAndTheirProducts(productsInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(
				"ID |   Product   | Supplier | Price \r\n" + "1 | Silver Pro Pan 999 | A | 25.0€\r\n"
						+ "2 | Silver Pot 833 | A | 25.0€\r\n" + "3 | Silver Pot 888 | B | 30.0€",
				systemOutRule.getLog().trim());
		productsInTheSystem.get(0).setProduct_id(0);
	}
	
	@Test
	public void testGetPriceOfPoduct() {
		double expected = 20.00;
		double actual = sudao.getPriceOfPoduct(0, suppliersInTheSystem, 0);
		Assert.assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testGetSupplierById() {
		Supplier expected = suppliersInTheSystem.get(0);
		Supplier actual = sudao.getSupplierById(0, suppliersInTheSystem);
		Assert.assertEquals(expected, actual);

	}
	
	@Test
	public void testGetSupplierByIdNull() {
		Supplier expected = null;
		Supplier actual = sudao.getSupplierById(7, suppliersInTheSystem);
		Assert.assertEquals(expected, actual);
	}
	

	@Test
	public void testGetPriceOfPoductNotFound() {
		double expected = 0;
		double actual = sudao.getPriceOfPoduct(10, suppliersInTheSystem, 0);
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetSupplierIdOfProduct() {
		int expected = 0;
		int actual = sudao.getSupplierIdOfProduct(0, suppliersInTheSystem);
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testGetSupplierIdOfProductReturnSystem() {
		int expected = 10000000;
		int actual = sudao.getSupplierIdOfProduct(10, suppliersInTheSystem);
		Assert.assertEquals(expected, actual);
	}
	

	@Test
	public void testChooseSupplier() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("0");
	    String data = sb.toString();
	    System.setIn(new ByteArrayInputStream(data.getBytes()));
	    Assert.assertEquals(0, sudao.chooseSupplier(suppliersInTheSystem));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testValidSupplier() {
		ArrayList<Integer> suppliers_ids = new ArrayList<Integer>();
		suppliers_ids.add(0);
		suppliers_ids.add(1);
		Assert.assertEquals(true, sudao.validSupplier(suppliers_ids, 0));
	}
	
	@Rule
	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();
	
	@Test
	public void testValidSupplierInvalid() {
		ArrayList<Integer> suppliers_ids = new ArrayList<Integer>();
		suppliers_ids.add(0);
		suppliers_ids.add(1);
		sudao.validSupplier(suppliers_ids, 4);
		Assert.assertEquals(
				"ERROR: you should enter an ID from the ones already on the screen.",
				systemErrRule.getLog().trim());
	}
	
	@Test
	public void testValidSupplierInvalid2() {
		ArrayList<Integer> suppliers_ids = new ArrayList<Integer>();
		suppliers_ids.add(0);
		suppliers_ids.add(1);
		Assert.assertEquals(false, sudao.validSupplier(suppliers_ids, 4));
	
	}

}
