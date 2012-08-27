package fly2.game.logic;

import fly2.game.logic.EnemyPlaneOutOfWorldStrategy;
import junit.framework.TestCase;

public class EnemyPlaneOutOfWorldStrategyTest extends TestCase {

	private EnemyPlaneOutOfWorldStrategy strategy;
	private Plane plane;
	private double worldWidth = 100.0;
	private double worldHeight = 50.0;

	public void setUp() {
		strategy = new EnemyPlaneOutOfWorldStrategy();
		plane = new Plane();
		plane.setHealth(1);
	}

	public void testReturnedLeft() {
		plane.setPosition(-10.0, 10.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(0.0, plane.getX(), 0.001);
		assertEquals(10.0, plane.getY(), 0.001);
	}

	public void testReturnedRight() {
		plane.setPosition(worldWidth + plane.getWidth() + 100.0, 10.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(worldWidth - plane.getWidth(), plane.getX(), 0.001);
		assertEquals(10.0, plane.getY(), 0.001);
	}

	public void testReturnedUp() {
		plane.setPosition(10.0, worldHeight + plane.getHeight() + 100.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(10.0, plane.getX(), 0.001);
		assertEquals(worldHeight - plane.getHeight(), plane.getY(), 0.001);
	}

	public void testDestroyDown1() {
		plane.setPosition(10.0, 0.0);
		assertFalse(plane.isDestroyed());
		strategy.activate(plane, worldWidth, worldHeight);
		assertTrue(plane.isDestroyed());
	}

	public void testDestroyDown2() {
		plane.setPosition(10.0, -1.0);
		assertFalse(plane.isDestroyed());
		strategy.activate(plane, worldWidth, worldHeight);
		assertTrue(plane.isDestroyed());
	}

	public void testDestroyDown3() {
		plane.setPosition(10.0, 10.0);
		assertFalse(plane.isDestroyed());
		strategy.activate(plane, worldWidth, worldHeight);
		assertFalse(plane.isDestroyed());
	}

	public void testDestroyDown4() {
		plane.setPosition(10.0, worldHeight - plane.getHeight());
		assertFalse(plane.isDestroyed());
		strategy.activate(plane, worldWidth, worldHeight);
		assertFalse(plane.isDestroyed());
	}
}
