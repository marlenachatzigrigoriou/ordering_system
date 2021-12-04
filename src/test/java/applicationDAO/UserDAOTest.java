package applicationDAO;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import application.Salesman;
import application.User;
import demo.DataGenerator;

public class UserDAOTest {

	private static DataGenerator dg = new DataGenerator();

	private static ArrayList<User> usersInTheSystem = new ArrayList<User>();

	@BeforeClass
	public static void initInputs() {
		User.setCount(0);
		usersInTheSystem = dg.usersGenerator();
	}

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testViewUser() {
		SalesmanDAO sadao = new SalesmanDAO();
		sadao.viewUser(usersInTheSystem.get(0));
		Assert.assertEquals("Lars Luxin", systemOutRule.getLog().trim());
	}

	@Test
	public void testAuthenticate() {
		User exected = usersInTheSystem.get(2);
		User actual = UserDAO.authenticate("vasa21", "yy8Ae!5g6Top", usersInTheSystem);
		Assert.assertEquals(exected, actual);
	}

	@Rule
	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Test
	public void testAuthenticate2() {
		User user = new Salesman("none", "none", "none");
		Assert.assertEquals(user, UserDAO.authenticate("vas1", "yy85g6Top", usersInTheSystem));
		Assert.assertEquals("Wrong username or password.", systemErrRule.getLog().trim());
	}

	@Test
	public void testAuthenticate3() {
		User user = new Salesman("none", "none", "none");
		Assert.assertEquals(user, UserDAO.authenticate("vas1", "yy8Ae!5g6Top", usersInTheSystem));
		Assert.assertEquals(user, UserDAO.authenticate("vasa21", "yy85g6Top", usersInTheSystem));
		Assert.assertEquals("Wrong username or password.\r\n" + "Wrong username or password.",
				systemErrRule.getLog().trim());
	}

}