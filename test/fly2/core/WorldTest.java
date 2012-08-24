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
		WorldItem item = addWorldItem(10, 20, 1, 1);
		Iterator<WorldItem> iter = world.getItems().iterator();
		assertSame(item, iter.next());
	}

	public void testAddRemoveItem() {
		WorldItem item = addWorldItem(10, 20, 1, 1);
		assertEquals(1, world.getItemsCount());
		world.removeItem(item);
		assertEquals(0, world.getItemsCount());
	}

	public void testOutOfWorld() {
		final boolean flag[] = new boolean[] { false };
		OutOfWorldStrategy strategy = new OutOfWorldStrategy<WorldItem>() {
			public void activate(WorldItem item, double worldWidth, double worldHeight) {
				flag[0] = true;
			}
		};
		WorldItem item = addWorldItem(-10, 1, 1, 1);
		world.registerOutOfWorldStrategy(item, strategy);
		world.activateOutOfWorldItems();
		assertTrue(flag[0]);
	}

	public void testOutOfWorldWithoutStrategies() {
		WorldItem itemInWorld = addWorldItem(10.0, 10.0, 1.0, 1.0);
		addWorldItem(-10, 20, 1, 1);
		addWorldItem(10, -20, 1, 1);
		addWorldItem(10, 20, 30000, 1);
		addWorldItem(10, 20, 1, 400000);
		assertEquals(5, world.getItemsCount());
		world.activateOutOfWorldItems();
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

	public void testImpact() {
		addWorldItem(10, 10, 1, 1);
		addWorldItem(11, 11, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		activateImpactFlag = false;
		world.activateItemsImpact();
		assertTrue(activateImpactFlag);
	}

	public void testImpactWithBackwardItems() {
		// соударение произойдет как WorldItem и UpdateableWorldItem
		addWorldItem(10, 10, 1, 1);
		addUpdateableWorldItem(11, 11, 1, 1);
		// а стратегия объявлена как ImpactStrategy<UpdateableWorldItem, WorldItem>
		world.registerImpactStrategy(new UpdateableWorldItemImpactStrategy());
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		activateImpactFlag = false;
		world.activateItemsImpact();
		// она должна сработать
		assertTrue(activateImpactFlag);
	}

	public void testImpactWithOutOfWorldFirstItem() {
		addWorldItem(2, 2, 1000000, 1000000);
		addWorldItem(2, 2, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		world.activateItemsImpact();
		assertFalse(activateImpactFlag);
	}

	public void testImpactWithOutOfWorldSecondItem() {
		addWorldItem(2, 2, 1, 1);
		addWorldItem(2, 2, 1000000, 1000000);
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		world.activateItemsImpact();
		assertFalse(activateImpactFlag);
	}

	public void testImpactWithOutOfWorldBothItems() {
		addWorldItem(0, 0, 1, 1);
		addWorldItem(0, 0, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		world.activateItemsImpact();
		assertFalse(activateImpactFlag);
	}

	public void testImpactWithDestoyedItem() {
		// один из объектов уничтожен
		addDestroyableWorldItem(11, 11, 1, 1).destroy();
		addWorldItem(10, 10, 1, 1);
		world.registerImpactStrategy(new DestroyableWorldItemImpactStrategy());
		activateImpactFlag = false;
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		world.activateItemsImpact();
		// стратегия не должна срабатывать
		assertFalse(activateImpactFlag);
	}

	public void testImpactWithoutStrategyRaiseErrorOff() {
		addWorldItem(10, 10, 1, 1);
		addWorldItem(11, 11, 1, 1);
		world.setRaiseErrorIfImpactStrategyNotFound(false);
		world.activateItemsImpact();
		assertTrue(true);
	}

	public void testImpactWithoutStrategyRaiseErrorOn() {
		addWorldItem(10, 10, 1, 1);
		addWorldItem(11, 11, 1, 1);
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		try {
			world.activateItemsImpact();
			fail();
		} catch (NoSuchWorldItemImpactStrategy exp) {
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
