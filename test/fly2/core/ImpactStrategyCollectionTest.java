package fly2.core;

import fly2.common.DuplicateKeyException;

import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class ImpactStrategyCollectionTest extends TestCase {

	private ImpactStrategyCollection collection;

	public void setUp() {
		collection = new ImpactStrategyCollection();
	}

	public void testAddDuplicateKey() {
		collection.add(new ABImpactStrategy());
		try {
			collection.add(new ABImpactStrategy());
			fail();
		} catch (DuplicateKeyException exp) {
		}
	}

	public void testContains() {
		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		assertTrue(collection.contains(AWorldItem.class, BWorldItem.class));
	}

	public void testContainsFalse1() {
		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		assertFalse(collection.contains(AWorldItem.class, CWorldItem.class));
	}

	public void testContainsFalse2() {
		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		assertFalse(collection.contains(BWorldItem.class, AWorldItem.class));
	}

	public void testGet() {
		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		ImpactStrategy<AWorldItem, BWorldItem> gettedStrategy = collection.getFor(AWorldItem.class, BWorldItem.class);
		assertSame(strategy, gettedStrategy);
	}

	public void testGetError() {
		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		try {
			collection.getFor(CWorldItem.class, BWorldItem.class);
			fail();
		} catch (NoSuchElementException exp) {
		}
	}

	public void testSize() {
		assertEquals(0, collection.size());

		ImpactStrategy<AWorldItem, BWorldItem> strategy = new ABImpactStrategy();
		collection.add(strategy);
		assertEquals(1, collection.size());

		collection.remove(strategy);
		assertEquals(0, collection.size());
	}

	private class AWorldItem extends WorldItem {
	}

	private class BWorldItem extends WorldItem {
	}

	private class CWorldItem extends WorldItem {
	}

	private class ABImpactStrategy implements ImpactStrategy<AWorldItem, BWorldItem> {

		public Class<AWorldItem> getFirstObjectClass() {
			return AWorldItem.class;
		}

		public Class<BWorldItem> getSecondObjectClass() {
			return BWorldItem.class;
		}

		public void activateImpact(AWorldItem first, BWorldItem second) {
		}
	}
}
