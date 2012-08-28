package fly2.game.logic;

import junit.framework.TestCase;

public class GameModelTest extends TestCase {

	private GameModel gameModel;

	public void setUp() {
		gameModel = new GameModel();
	}

	public void testNew() {
		gameModel = new GameModel();
		assertNotNull(gameModel.getWorld());
		assertTrue(gameModel.getWorld().getWidth() > 0);
		assertTrue(gameModel.getWorld().getHeight() > 0);
		assertNotNull(gameModel.getPlayerPlane());
	}
}
