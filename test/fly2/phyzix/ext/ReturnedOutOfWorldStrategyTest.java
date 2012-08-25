package fly2.phyzix.ext;

import fly2.phyzix.WorldItem;
import fly2.phyzix.ext.ReturnedOutOfWorldStrategy;
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
		item.setPosition(-10.0, 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(0.0, item.getX(), 0.001);
		assertEquals(10.0, item.getY(), 0.001);
	}

	public void testOutFromRight() {
		item.setPosition(worldWidth + 10.0, 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(worldWidth - item.getWidth(), item.getX(), 0.001);
		assertEquals(10.0, item.getY(), 0.001);
	}

	public void testOutFromDown() {
		item.setPosition(10.0, -10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(10.0, item.getX(), 0.001);
		assertEquals(0.0, item.getY(), 0.001);
	}

	public void testOutFromUp() {
		item.setPosition(10.0, worldHeight + 10.0);
		strategy.activate(item, worldWidth, worldHeight);
		assertEquals(10.0, item.getX(), 0.001);
		assertEquals(worldHeight - item.getHeight(), item.getY(), 0.001);
	}
}
