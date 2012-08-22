package fly2.core;

import java.util.*;
import fly2.unittest.*;

public class WorldTest extends WorldTestCase {

	private double worldWidth = 100.0;
	private double worldHeight = 200.0;

	public void setUp() {
		world = new World(worldWidth, worldHeight, new ImpactChecker());
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
		UpdateableWorldItem item1 = addUpdateableWorldItem();
		UpdateableWorldItem item2 = addUpdateableWorldItem();

		assertFalse(item1.isUpdated());
		assertFalse(item2.isUpdated());

		world.updateItems();

		assertTrue(item1.isUpdated());
		assertTrue(item2.isUpdated());
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
}
