package fly2.game.logic;

import junit.framework.TestCase;

public class GameWorldTest extends TestCase {

	private GameWorld gameWorld;
	private double worldWidth = 100.0;
	private double worldHeight = 200.0;

	public void setUp() {
		gameWorld = new GameWorld(worldWidth, worldHeight);
	}

	public void testSize() {
		assertEquals(worldWidth, gameWorld.getWidth());
		assertEquals(worldHeight, gameWorld.getHeight());
	}
}
