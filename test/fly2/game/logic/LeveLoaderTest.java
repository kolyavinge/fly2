package fly2.game.logic;

import fly2.game.frontend.GameWorld;
import fly2.game.frontend.LevelContext;
import fly2.game.frontend.Plane;
import fly2.game.logic.LevelLoader;
import junit.framework.TestCase;

public class LeveLoaderTest extends TestCase {

	private LevelLoader levelLoader;

	public void setUp() {
		levelLoader = new LevelLoader();
	}

	public void testGetLevelsCount() {
		assertEquals(1, levelLoader.getLevelsCount());
	}

	public void testGetCurrentLevel() {
//		LevelContext levelContext = levelLoader.getLevelByNumber(1);
//
//		GameWorld world = levelContext.getWorld();
//		assertNotNull(world);
//		assertEquals(20.0, world.getWidth(), 0.001);
//		assertEquals(100.0, world.getHeight(), 0.001);
//
//		Plane playerPlane = levelContext.getPlayerPlane();
//		assertNotNull(playerPlane);
//		assertEquals(world.getWidth() / 2.0, playerPlane.getMiddleX(), 0.001);
//		assertEquals(0, playerPlane.getY(), 0.001);
//		assertEquals(10, playerPlane.getHealth());
//		assertNotNull(playerPlane.getWeapon());
//
//		assertEquals(0, levelContext.getEnemyPlanesCount());
	}

	public void testZeroLevelNumber() {
		try {
			levelLoader.getLevelByNumber(0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testBigLevelNumber() {
		try {
			levelLoader.getLevelByNumber(10);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testNegativeLevelNumber() {
		try {
			levelLoader.getLevelByNumber(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
