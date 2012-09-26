package fly2.phyzix;

import fly2.unittest.framework.WorldTestCase;
import fly2.unittest.stub.UpdateableWorldItem;

import java.util.Iterator;

public class WorldTest extends WorldTestCase {

	private double worldWidth = 100.0;
	private double worldHeight = 200.0;

	public void setUp() {
		world = new World(worldWidth, worldHeight);
	}

	public void testNew() {
		world = new World(worldWidth, worldHeight);
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

	public void testInWorld1() {
		WorldItem item = new WorldItem();
		item.setPosition(10.0, 10.0);
		item.setSize(1.0, 1.0);
		assertTrue(world.inWorld(item));
	}

	public void testInWorld2() {
		WorldItem item = new WorldItem();
		item.setPosition(0.0, 0.0);
		item.setSize(1.0, 1.0);
		assertTrue(world.inWorld(item));
	}

	public void testInWorld3() {
		WorldItem item = new WorldItem();
		item.setPosition(world.getWidth() - 1.0, world.getHeight() - 1.0);
		item.setSize(1.0, 1.0);
		assertTrue(world.inWorld(item));
	}

	public void testInWorld4() {
		WorldItem item = new WorldItem();
		item.setPosition(-1.0, 1.0);
		item.setSize(1.0, 1.0);
		assertFalse(world.inWorld(item));
	}

	public void testInWorld5() {
		WorldItem item = new WorldItem();
		item.setPosition(1.0, -1.0);
		item.setSize(1.0, 1.0);
		assertFalse(world.inWorld(item));
	}

	public void testActivateOutOfWorldItems() {
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

	public void testActivateOutOfWorldItemsWithoutStrategies() {
		WorldItem itemInWorld = addWorldItem(10.0, 10.0, 1.0, 1.0);
		WorldItem item1 = addWorldItem(-10, 20, 1, 1);
		WorldItem item2 = addWorldItem(10, -20, 1, 1);
		WorldItem item3 = addWorldItem(10, 20, 30000, 1);
		WorldItem item4 = addWorldItem(10, 20, 1, 400000);
		assertEquals(5, world.getItemsCount());
		
		world.activateOutOfWorldItems();
		
		assertEquals(5, world.getItemsCount());
		assertFalse(itemInWorld.isDestroyed());
		assertTrue(item1.isDestroyed());
		assertTrue(item2.isDestroyed());
		assertTrue(item3.isDestroyed());
		assertTrue(item4.isDestroyed());
	}
	
	public void testActiveOutOfWorld() {
		WorldItem itemInWorld = addWorldItem(10.0, 10.0, 1.0, 1.0);
		WorldItem item1 = addWorldItem(-10, 20, 1, 1);
		WorldItem item2 = addWorldItem(10, -20, 1, 1);
		WorldItem item3 = addWorldItem(10, 20, 30000, 1);
		WorldItem item4 = addWorldItem(10, 20, 1, 400000);
		
		world.activateOutOfWorld(itemInWorld);
		world.activateOutOfWorld(item1);
		world.activateOutOfWorld(item2);
		world.activateOutOfWorld(item3);
		world.activateOutOfWorld(item4);
		
		assertFalse(itemInWorld.isDestroyed());
		assertTrue(item1.isDestroyed());
		assertTrue(item2.isDestroyed());
		assertTrue(item3.isDestroyed());
		assertTrue(item4.isDestroyed());
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
		addWorldItem(-1, -1, 1, 1);
		addWorldItem(-1, -1, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
		activateImpactFlag = false;
		world.setRaiseErrorIfImpactStrategyNotFound(true);
		world.activateItemsImpact();
		assertFalse(activateImpactFlag);
	}

	public void testImpactWithDestoyedItem() {
		// один из объектов уничтожен
		addWorldItem(11, 11, 1, 1).destroy();
		addWorldItem(10, 10, 1, 1);
		world.registerImpactStrategy(new TestImpactStrategy());
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

	public void testRemoveAllDestroyedItems() {
		WorldItem item1 = addWorldItem();
		item1.destroy();
		WorldItem item2 = addWorldItem();
		assertEquals(2, world.getItemsCount());
		world.removeDestroyedItems();
		assertEquals(1, world.getItemsCount());
		assertSame(item2, world.getItems().iterator().next());
	}
}
