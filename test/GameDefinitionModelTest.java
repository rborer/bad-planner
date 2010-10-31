import models.GameDefinition;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class GameDefinitionModelTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAll();
	}

	@Test
	public void testValidation() {
		// should raise exceptions but don't...
		new GameDefinition(null, null).save();
		new GameDefinition("Test", null).save();
	}
	@Test
	public void createAndRetrieveGames() {
		Fixtures.load("games.yml");

		assertEquals(2, GameDefinition.count());

		final GameDefinition bad = GameDefinition.find("byDescriptionLike",
				"%guignolos").first();
		assertNotNull(bad);
	}

}
