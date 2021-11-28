package applicationDAO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.*;
import demo.DataGenerator;

public class StockDAOTest {

	private StockDAO sdao = new StockDAO();
	private static DataGenerator dg = new DataGenerator();
	private static ArrayList<Stock> stockOfProductsInTheSystem = new ArrayList<Stock>();
	private static ArrayList<Product> productsInTheSystem = new ArrayList<Product>();
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
		outgoingOrdersInTheSystem = dg.outgoingOrdersGenerator();
	}

	@Test
	public void testUpdateStockLowerLimit() throws IOException {
		Assert.assertEquals(4, sdao.updateStockLowerLimit(4, stockOfProductsInTheSystem.get(0)));
		sdao.updateStockLowerLimit(5, stockOfProductsInTheSystem.get(0));
	}

	@Test
	public void testGetStockByStockId() throws IOException {
		Stock expected = stockOfProductsInTheSystem.get(1);
		Assert.assertEquals(expected, sdao.getStockByStockId(1, stockOfProductsInTheSystem));
		// no case getStockByStockId returns null, because i check the input before
		// calling the method
	}

	@Test
	public void testReduceStock() throws IOException {
		List<Stock> expected = stockOfProductsInTheSystem;
		ArrayList<Stock> stockOfProductsInTheSystem = new ArrayList<Stock>();
		stockOfProductsInTheSystem.add(new Stock(0, 135, 100));
		stockOfProductsInTheSystem.add(new Stock(1, 95, 100));
		stockOfProductsInTheSystem.add(new Stock(2, 65, 100));
		stockOfProductsInTheSystem.add(new Stock(3, 25, 100));
		int[][] products_items = { { 0, 35 }, { 1, 20 }, { 2, 15 }, { 3, 0 } };
		List<Stock> actuals = sdao.reduceStock(stockOfProductsInTheSystem, products_items);
		Assert.assertTrue(expected.equals(actuals));
	}

	@Test
	public void testIncreaseStock() throws IOException {
		List<Stock> expected = stockOfProductsInTheSystem;
		ArrayList<Stock> s = new ArrayList<Stock>();
		s.add(new Stock(0, 90, 100));
		s.add(new Stock(1, 75, 100));
		s.add(new Stock(2, 20, 100));
		s.add(new Stock(3, 25, 100));
		int[][] products_items = { { 0, 10 }, { 1, 0 }, { 2, 30 }, { 3, 0 } };
		List<Stock> actuals = sdao.increaseStock(s, products_items);
		Assert.assertTrue(expected.equals(actuals));
	}

	@Test
	public void testStoreStockOfProductsInMemory() {
		ArrayList<Stock> expected = new ArrayList<Stock>();
		expected.add(new Stock(0, 100, 100));
		expected.add(new Stock(1, 75, 100));
		expected.add(new Stock(2, 50, 100));
		expected.add(new Stock(3, 25, 100));
		expected.add(new Stock(4, 100, 100));
		ArrayList<Stock> actuals = sdao.storeStockOfProductsInMemory(stockOfProductsInTheSystem,
				new Stock(4, 100, 100));
		Assert.assertEquals(expected, actuals);
		stockOfProductsInTheSystem.remove(4); // so i can continue the rest of the tests with my known data
	}

	@Test
	public void testCheckStockCapacityEnoughCase() throws IOException {
		boolean expected = true;
		productsInTheSystem = dg.productsGenerator();
		boolean actuals = sdao.checkStockCapacity(0, 5, stockOfProductsInTheSystem, productsInTheSystem,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(expected, actuals);
	}

	@Test
	public void test1CheckStockCapacityLowerThanStockLowerLimitCase() throws IOException {
		boolean expected = true;
		boolean actuals = sdao.checkStockCapacity(2, 45, stockOfProductsInTheSystem, productsInTheSystem,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(expected, actuals);
		OutgoingOrderDAO oudao = new OutgoingOrderDAO();
		oudao.getAllOutgoingOrderInformationInTheSystem().clear(); // so i can continue the rest of the tests with my
																	// known data
	}

	@Test
	public void test2CheckStockCapacityLowerThanStockLowerLimitCase() throws IOException {
		boolean expected = true;
		boolean actuals = sdao.checkStockCapacity(2, 50, stockOfProductsInTheSystem, productsInTheSystem,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(expected, actuals);
		OutgoingOrderDAO oudao = new OutgoingOrderDAO();
		oudao.getAllOutgoingOrderInformationInTheSystem().clear(); // so i can continue the rest of the tests with my
																	// known data
	}

	@Test
	public void testCheckStockCapacityNotEnoughCase() throws IOException {
		boolean expected = false;
		boolean actuals = sdao.checkStockCapacity(3, 26, stockOfProductsInTheSystem, productsInTheSystem,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(expected, actuals);
	}

	@Test
	public void testCheckStockCapacityNoItems() throws IOException {
		boolean expected = true;
		boolean actuals = sdao.checkStockCapacity(3, 0, stockOfProductsInTheSystem, productsInTheSystem,
				outgoingOrdersInTheSystem, suppliersInTheSystem);
		Assert.assertEquals(expected, actuals);
	}

}
