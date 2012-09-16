package fly2.game.logic;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;
import fly2.app.AssetFileReader;

import java.util.Iterator;

public class GameWorldFactoryTest extends InstrumentationTestCase {

	private GameWorldFactory factory;
	private GameWorld gameWorld;
	private Iterator<Plane> enemyIterator;

	public void setUp() {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		AssetFileReader reader = new AssetFileReader(assetManager);
		factory = new GameWorldFactory(reader);
	}

	public void testWorldCount() {
		assertEquals(1, factory.getWorldsCount());
	}

	public void testMakeWorld() {
		makeWorld(0);
		assertWorldSize(20, 150);
		assertEnemyCount(10);
		assertEnemyPosition(10, 30);
		assertEnemyPosition(15, 40);
		assertEnemyPosition(4, 50);
		assertEnemyPosition(12, 60);
		assertEnemyPosition(2, 70);
		assertEnemyPosition(10, 80);
		assertEnemyPosition(14, 90);
		assertEnemyPosition(12, 100);
		assertEnemyPosition(8, 110);
		assertEnemyPosition(9, 120);
	}

	public void testBigWorldNumber() {
		try {
			factory.makeWorld(factory.getWorldsCount());
			fail();
		} catch (GameWorldFactoryException exp) {
		}
	}

	public void testNegativWorldNumber() {
		try {
			makeWorld(-1);
			fail();
		} catch (GameWorldFactoryException exp) {
		}
	}

	public void testNullReader() {
		try {
			new GameWorldFactory(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private void makeWorld(int worldNumber) {
		gameWorld = factory.makeWorld(worldNumber);
		assertNotNull(gameWorld);
		enemyIterator = gameWorld.getEnemyPlanes().iterator();
	}

	private void assertWorldSize(double width, double height) {
		assertEquals(width, gameWorld.getWidth());
		assertEquals(height, gameWorld.getHeight());
	}

	private void assertEnemyCount(int count) {
		assertEquals(count, gameWorld.getEnemyPlanesCount());
	}

	private void assertEnemyPosition(double x, double y) {
		Plane enemy = enemyIterator.next();
		assertEquals(x, enemy.getX());
		assertEquals(y, enemy.getY());
	}
}
