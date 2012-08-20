package fly2.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class WorldTest extends TestCase {

	private double worldWidth = 100.0;
	private double worldHeight = 200.0;
	private World world;
	private int updateables;
	private boolean activateImpactFlag;

	public void setUp() {
		world = new World(worldWidth, worldHeight);
		updateables = 0;
	}

	public void testNew() {
		assertEquals(worldWidth, world.getWidth());
		assertEquals(worldHeight, world.getHeight());
	}

	public void testWrongWorldSize() {
		try {
			new World(0, 1);
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(1, 0);
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(-1, 1);
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(1, -1);
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}
	}

	public void testGetItems() {
		WorldItem item = addWorldItem(10, 20, 30, 40);
		Iterator<WorldItem> iter = world.getItems().iterator();
		assertSame(item, iter.next());
	}

	public void testAddRemoveItem() {
		WorldItem item = addWorldItem(10, 20, 30, 40);
		assertEquals(1, world.getItemsCount());
		world.removeItem(item);
		assertEquals(0, world.getItemsCount());
	}

	public void testRemoveOutOfWorldItems() {
		WorldItem itemInWorld = addWorldItem(0.0, 0.0, 1.0, 1.0);
		addWorldItem(-10, 20, 30, 40);
		addWorldItem(10, -20, 30, 40);
		addWorldItem(10, 20, 30000, 40);
		addWorldItem(10, 20, 30, 400000);
		assertEquals(5, world.getItemsCount());
		world.removeOutOfWorldItems();
		assertEquals(1, world.getItemsCount());
		assertSame(itemInWorld, world.getItems().iterator().next());
	}

	public void testUpdateAllItems() {
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		world.updateAllItems();
		assertEquals(3, updateables);
	}

	public void testActivateItemsImpact() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.setImpactChecker(new ImpactChecker());
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.activateItemsImpact();
		assertTrue(activateImpactFlag);
	}

	public void testActivateItemsImpactWithoutStrategyRaiseErrorOff() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.setImpactChecker(new ImpactChecker());
		world.setRaiseErrorIfImpactStrategyNotFound(false);
		world.activateItemsImpact();
		assertTrue(true);
	}

	public void testActivateItemsImpactWithoutStrategyRaiseErrorOn() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.setImpactChecker(new ImpactChecker());
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		try {
			world.activateItemsImpact();
			fail();
		} catch (NoSuchElementException exp) {
		}
	}

	public void testActivateItemsImpactWithoutImpactChecker() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		try {
			world.activateItemsImpact();
			fail();
		} catch (IllegalStateException exp) {
		}
	}

	public void testSetNullImpactChecker() {
		try {
			world.setImpactChecker(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private WorldItem addUpdateableWorldItem() {
		WorldItem item = new UpdateableWorldItem();
		world.addItem(item);

		return item;
	}

	private WorldItem addWorldItem(double leftUpX, double leftUpY, double width, double height) {
		WorldItem item = new WorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	private class TestImpactStrategy implements ImpactStrategy<WorldItem, WorldItem> {

		public Class<WorldItem> getFirstObjectClass() {
			return WorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(WorldItem left, WorldItem right) {
			activateImpactFlag = true;
		}
	}

	private class UpdateableWorldItem extends WorldItem implements Updateable {

		public void update() {
			updateables++;
		}
	}
}
