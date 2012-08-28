package fly2.game.frontend;

import fly2.unittest.TestGameModel;
import junit.framework.TestCase;

public class GameModelFactoryTest extends TestCase {

	private GameModelFactory factory;

	public void setUp() {
		factory = GameModelFactory.getInstance();
	}

	public void testCreateModel() {
		System.setProperty(GameModelFactory.getGameModelClassPropName(), TestGameModel.class.getName());

		GameModel gameModel1 = factory.createModel();
		assertTrue(gameModel1 instanceof TestGameModel);

		GameModel gameModel2 = factory.createModel();
		assertTrue(gameModel2 instanceof TestGameModel);

		assertNotSame(gameModel1, gameModel2);
	}

	public void testWrongClassModelName() {
		System.setProperty(GameModelFactory.getGameModelClassPropName(), String.class.getName());
		try {
			factory.createModel();
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
