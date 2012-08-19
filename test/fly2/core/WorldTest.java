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

	public void testWrongSize() {
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
		WorldItem item = addWorldItem(new WorldItem(10, 20, 30, 40));
		Iterator<WorldItem> iter = world.getItems().iterator();
		assertSame(item, iter.next());
	}

	public void testAddRemoveItem() {
		WorldItem item = addWorldItem(new WorldItem(10, 20, 30, 40));
		assertEquals(1, world.getItemsCount());
		world.removeItem(item);
		assertEquals(0, world.getItemsCount());
	}

	public void testOutOfWorldBounds() {
		WorldItem item = new WorldItem(-10, 20, 30, 40);
		try {
			world.addItem(item);
			fail();
		} catch (ItemOutOfWorldException exp) {
			assertSame(item, exp.getWorldItem());
		}

		item = new WorldItem(10, -20, 30, 40);
		try {
			world.addItem(item);
			fail();
		} catch (ItemOutOfWorldException exp) {
			assertSame(item, exp.getWorldItem());
		}

		item = new WorldItem(10, 20, 30000, 40);
		try {
			world.addItem(item);
			fail();
		} catch (ItemOutOfWorldException exp) {
			assertSame(item, exp.getWorldItem());
		}

		item = new WorldItem(10, 20, 30, 400000);
		try {
			world.addItem(item);
			fail();
		} catch (ItemOutOfWorldException exp) {
			assertSame(item, exp.getWorldItem());
		}
	}

	public void testUpdateAllItems() {
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		world.updateAllItems();
		assertEquals(3, updateables);
	}

	public void testActivateItemsImpact() {
		addWorldItem(new WorldItem(0, 0, 1, 1));
		addWorldItem(new WorldItem(1, 1, 1, 1));
		world.setImpactChecker(new ImpactChecker());
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.activateItemsImpact();
		assertTrue(activateImpactFlag);
	}

	public void testActivateItemsImpactWithoutStrategy() {
		addWorldItem(new WorldItem(0, 0, 1, 1));
		addWorldItem(new WorldItem(1, 1, 1, 1));
		world.setImpactChecker(new ImpactChecker());
		try {
			world.activateItemsImpact();
			fail();
		} catch (NoSuchElementException exp) {
		}
	}

	public void testActivateItemsImpactWithoutImpactChecker() {
		addWorldItem(new WorldItem(0, 0, 1, 1));
		addWorldItem(new WorldItem(1, 1, 1, 1));
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

	private WorldItem addWorldItem(WorldItem item) {
		world.addItem(item);
		return item;
	}

	private class TestImpactStrategy implements ImpactStrategy {

		public Class getLeftObjectClass() {
			return WorldItem.class;
		}

		public Class getRightObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(Object leftObject, Object rightObject) {
			activateImpactFlag = true;
		}
	}

	private class UpdateableWorldItem extends WorldItem implements Updateable {

		public void update() {
			updateables++;
		}
	}
}
