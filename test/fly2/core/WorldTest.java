package fly2.core;

import java.util.Iterator;

import junit.framework.TestCase;

public class WorldTest extends TestCase {

	private double worldWidth = 100.0;
	private double worldHeight = 200.0;
	private World world;
	private int updateables;

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

	public void testItemsCount() {
		WorldItem item = new WorldItem(10, 20, 30, 40);
		world.addItem(item);
		assertEquals(1, world.getItemsCount());
	}

	public void testGetItems() {
		WorldItem item = new WorldItem(10, 20, 30, 40);
		world.addItem(item);
		Iterator<WorldItem> iter = world.getItems().iterator();
		assertSame(item, iter.next());
	}

	public void testAddRemoveItem() {
		WorldItem item = new WorldItem(10, 20, 30, 40);
		world.addItem(item);
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

	public void testUpdate() {
		world.addItem(new UpdateableWorldItem());
		world.addItem(new UpdateableWorldItem());
		world.addItem(new UpdateableWorldItem());

		world.update();

		assertEquals(3, updateables);
	}
	
	private class UpdateableWorldItem extends WorldItem implements Updateable {
		public void update() {
			updateables++;
		}
	}
}
