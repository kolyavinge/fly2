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
		world = new World(worldWidth, worldHeight, new ImpactChecker());
		updateables = 0;
	}

	public void testNew() {
		ImpactChecker impactChecker = new ImpactChecker();
		world = new World(worldWidth, worldHeight, impactChecker);
		assertEquals(worldWidth, world.getWidth());
		assertEquals(worldHeight, world.getHeight());
		assertSame(impactChecker, world.getImpactChecker());
	}

	public void testWrongWorldSize() {
		try {
			new World(0, 1, new ImpactChecker());
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(1, 0, new ImpactChecker());
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(-1, 1, new ImpactChecker());
			fail();
		} catch (IllegalArgumentException exp) {
			assertTrue(true);
		}

		try {
			new World(1, -1, new ImpactChecker());
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

	public void testUpdateItems() {
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		addUpdateableWorldItem();
		world.updateItems();
		assertEquals(3, updateables);
	}

	public void testActivateItemsImpact() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		activateImpactFlag = false;
		world.activateItemsImpact();
		assertTrue(activateImpactFlag);
	}

	public void testActivateItemsImpactWithBackwardItems() {
		// соударение произойдет как WorldItem и UpdateableWorldItem
		addWorldItem(0, 0, 1, 1);
		addUpdateableWorldItem(1, 1, 1, 1);
		// а стратегия объявлена как ImpactStrategy<UpdateableWorldItem, WorldItem>
		world.registerImpactStrategy(new UpdateableWorldItemImpactStrategy());
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		activateImpactFlag = false;
		world.activateItemsImpact();
		// она должна сработать
		assertTrue(activateImpactFlag);
	}

	public void testActivateItemsImpactWithDestoyedItem() {
		// один из объектов уничтожен
		addDestroyableWorldItem(1, 1, 1, 1).destroy();
		addWorldItem(0, 0, 1, 1);
		world.registerImpactStrategy(new DestroyableWorldItemImpactStrategy());
		activateImpactFlag = false;
		world.activateItemsImpact();
		// стратегия не должна срабатывать
		assertFalse(activateImpactFlag);
	}

	public void testActivateItemsImpactWithoutStrategyRaiseErrorOff() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.setRaiseErrorIfImpactStrategyNotFound(false);
		world.activateItemsImpact();
		assertTrue(true);
	}

	public void testActivateItemsImpactWithoutStrategyRaiseErrorOn() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(1, 1, 1, 1);
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		try {
			world.activateItemsImpact();
			fail();
		} catch (NoSuchElementException exp) {
		}
	}

	public void testCreateWithNullImpactChecker() {
		try {
			new World(1.0, 1.0, null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullImpactChecker() {
		try {
			world.setImpactChecker(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testRemoveAllDestroyedItems() {
		DestroyableWorldItem item1 = addDestroyableWorldItem();
		item1.destroy();
		DestroyableWorldItem item2 = addDestroyableWorldItem();
		assertEquals(2, world.getItemsCount());
		world.removeDestroyedItems();
		assertEquals(1, world.getItemsCount());
		assertSame(item2, world.getItems().iterator().next());
	}

	private WorldItem addUpdateableWorldItem() {
		WorldItem item = new UpdateableWorldItem();
		world.addItem(item);

		return item;
	}

	private WorldItem addUpdateableWorldItem(double leftUpX, double leftUpY, double width, double height) {
		WorldItem item = new UpdateableWorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	private DestroyableWorldItem addDestroyableWorldItem() {
		DestroyableWorldItem item = new DestroyableWorldItem();
		world.addItem(item);

		return item;
	}

	private DestroyableWorldItem addDestroyableWorldItem(double leftUpX, double leftUpY, double width, double height) {
		DestroyableWorldItem item = new DestroyableWorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
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

	private class DestroyableWorldItemImpactStrategy implements ImpactStrategy<DestroyableWorldItem, WorldItem> {

		public Class<DestroyableWorldItem> getFirstObjectClass() {
			return DestroyableWorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(DestroyableWorldItem left, WorldItem right) {
			activateImpactFlag = true;
		}
	}

	private class UpdateableWorldItemImpactStrategy implements ImpactStrategy<UpdateableWorldItem, WorldItem> {

		public Class<UpdateableWorldItem> getFirstObjectClass() {
			return UpdateableWorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(UpdateableWorldItem left, WorldItem right) {
			activateImpactFlag = true;
		}
	}

	private class UpdateableWorldItem extends WorldItem implements Updateable {

		public void update() {
			updateables++;
		}
	}

	private class DestroyableWorldItem extends WorldItem implements Destroyable {

		private boolean isDestroyed = false;

		public boolean isDestroyed() {
			return isDestroyed;
		}

		public void destroy() {
			isDestroyed = true;
		}
	}
}
