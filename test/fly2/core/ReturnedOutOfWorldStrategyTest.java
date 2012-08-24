package fly2.core;

import junit.framework.TestCase;

public class ReturnedOutOfWorldStrategyTest extends TestCase {

	private ReturnedOutOfWorldStrategy<WorldItem> strategy;
	private WorldItem item;
	private double worldWidth = 100.0;
	private double worldHeight = 200.0;

	public void setUp() {
		strategy = new ReturnedOutOfWorldStrategy<WorldItem>();
		item = new WorldItem();
		item.setSize(1.0, 1.0);
	}

	public void testOutFromLeft() {
		item.setLeftUpPoint(-10.0, 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(0.0, item.getBounds().getLeftUpX(), 0.001);
		assertEquals(10.0, item.getBounds().getLeftUpY(), 0.001);
	}

	public void testOutFromRight() {
		item.setLeftUpPoint(worldWidth + 10.0, 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(worldWidth - item.getWidth(), item.getBounds().getLeftUpX(), 0.001);
		assertEquals(10.0, item.getBounds().getLeftUpY(), 0.001);
	}

	public void testOutFromDown() {
		item.setLeftUpPoint(10.0, -10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(10.0, item.getBounds().getLeftUpX(), 0.001);
		assertEquals(item.getHeight(), item.getBounds().getLeftUpY(), 0.001);
	}

	public void testOutFromUp() {
		item.setLeftUpPoint(10.0, worldHeight + 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(10.0, item.getBounds().getLeftUpX(), 0.001);
		assertEquals(worldHeight - item.getHeight(), item.getBounds().getLeftUpY(), 0.001);
	}
}
