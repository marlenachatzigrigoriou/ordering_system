package applicationDAO;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import application.*;
import demo.DataGenerator;

import org.junit.Assert;

public class ProductDAOTest {

	private ProductDAO pdao = new ProductDAO();
	private static DataGenerator dg = new DataGenerator();
	private static ArrayList<Stock> stockOfProductsInTheSystem = new ArrayList<Stock>();
	private static ArrayList<Product> productsInTheSystem = new ArrayList<Product>();
	private static ArrayList<Order> incomingOrdersInTheSystem = new ArrayList<Order>();
	private static ArrayList<Order> outgoingOrdersInTheSystem = new ArrayList<Order>();
	private static ArrayList<Supplier> suppliersInTheSystem = new ArrayList<Supplier>();

	@BeforeClass
	public static void initInputs() {
		Order.setCount(0);
		Supplier.setCount(0);
		Product.setCount(0);
		User.setCount(0);
		productsInTheSystem = dg.productsGenerator();
		suppliersInTheSystem = dg.supplierGenerator();
		stockOfProductsInTheSystem = dg.stockGenerator(productsInTheSystem);
		incomingOrdersInTheSystem = dg.incomingOrdersGenerator();
		outgoingOrdersInTheSystem = dg.outgoingOrdersGenerator();
	}

	@Test
	public void testStoreProductsInMemory() {
		ProductFactory pfactory = new ProductFactory();
		productsInTheSystem.add(pfactory.createProduct("New Product", 80.00, 120.00, 0.80, "Aluminum", "Pan"));
		Product p = productsInTheSystem.get(productsInTheSystem.size() - 1);
		Assert.assertEquals(productsInTheSystem, pdao.storeProductsInMemory(productsInTheSystem, p));
		productsInTheSystem.remove(p);
	}

	@Test
	public void testGetProductById() {
		Product expected = productsInTheSystem.get(0);
		Product actual = pdao.getProductById(0, productsInTheSystem);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetProductByIdNulCase() {
		Product actual = pdao.getProductById(7, productsInTheSystem);
		Assert.assertNull(actual);
	}

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testListProductsOfOrder() {
		ArrayList<Product> expected = new ArrayList<Product>();
		expected.add(productsInTheSystem.get(0));
		expected.add(productsInTheSystem.get(2));
		expected.add(productsInTheSystem.get(1));
		ArrayList<Product> actual = pdao.listProductsOfOrder(((IncomingOrder) incomingOrdersInTheSystem.get(0)),
				productsInTheSystem);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testListProductsOfOrderPrint() {
		ArrayList<Product> expected = new ArrayList<Product>();
		expected.add(productsInTheSystem.get(0));
		expected.add(productsInTheSystem.get(2));
		expected.add(productsInTheSystem.get(1));
		pdao.listProductsOfOrder(((IncomingOrder) incomingOrdersInTheSystem.get(0)),
				productsInTheSystem);
		Assert.assertEquals(
				"0 | Silver Pan 893 , 120.0 inches , 1.0 kg , Aluminum , 40.0€ \r\n"
						+ "2 | Silver Pot 833 , 120.0 inches , 1.5 kg , Aluminum , 50.0€ \r\n"
						+ "1 | Silver Pro Pan 999 , 130.0 inches , 0.9 kg , Cast Iron , 50.0€",
				systemOutRule.getLog().trim());
	}
	

	@Test
	public void testPrintProductsOfOutgoingOrder() {
		pdao.printProductsOfOutgoingOrder(productsInTheSystem, suppliersInTheSystem, 0,
				(OutgoingOrder) outgoingOrdersInTheSystem.get(0));
		Assert.assertEquals("Silver Pan 893           50           1000.0€\r\n"
				+ "    Silver Pro Pan 999           30           750.0€", systemOutRule.getLog().trim());
	}

	@Test
	public void testPrintProductsOfOutgoingOrderItemZero() {
		OutgoingOrderDAO oudao = new OutgoingOrderDAO();
		OutgoingOrder o = (OutgoingOrder) outgoingOrdersInTheSystem.get(0);
		oudao.getAllOutgoingOrderInformationInTheSystem().get(o).get(3).set(0, 0);
		pdao.printProductsOfOutgoingOrder(productsInTheSystem, suppliersInTheSystem, 0, o);
		Assert.assertEquals("Silver Pro Pan 999           30           750.0€", systemOutRule.getLog().trim());
		oudao.getAllOutgoingOrderInformationInTheSystem().get(o).get(3).set(0, 50);
	}

}
