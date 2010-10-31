import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserModelTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAll();
	}

	@Test
	public void createAndRetrieveUser() {
		Fixtures.load("users.yml");

		final User bob = User.find("byEmail", "toto.ro@gmail.com").first();
		final User mickey = User.find("byFirstnameAndLastname", "Mickey",
				"Mouse").first();

		// Test
		assertNotNull(bob);
		assertEquals("Toto", bob.firstname);

		assertNotNull(mickey);
		assertEquals("mickey.mouse@gmail.com", mickey.email);

		assertEquals(3, User.count());
	}

	@Test
	public void tryConnectAsUser() {
		Fixtures.load("users.yml");

		// Test
		assertNotNull(User.connect("bob@gmail.com", "secret"));
		assertNull(User.connect("bob@gmail.com", "badpassword"));
		assertNull(User.connect("tom@gmail.com", "secret"));
	}
}
