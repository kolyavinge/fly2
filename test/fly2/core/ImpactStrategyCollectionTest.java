package fly2.core;

import java.util.NoSuchElementException;
import fly2.core.*;
import junit.framework.TestCase;

public class ImpactStrategyCollectionTest extends TestCase {

	private ImpactStrategyCollection collection;

	public void setUp() {
		collection = new ImpactStrategyCollection();
	}

	public void testContains() {
		ImpactStrategy strategy = getImpactStrategyStringDouble();
		collection.add(strategy);
		assertTrue(collection.contains(String.class, Double.class));
	}

	public void testContainsFalse() {
		ImpactStrategy strategy = getImpactStrategyStringDouble();
		collection.add(strategy);
		assertFalse(collection.contains(Integer.class, Math.class));
	}

	public void testGet() {
		ImpactStrategy strategy = getImpactStrategyStringDouble();
		collection.add(strategy);
		ImpactStrategy gettedStrategy = collection.getFor(String.class, Double.class);
		assertSame(strategy, gettedStrategy);
	}

	public void testGetError() {
		ImpactStrategy strategy = getImpactStrategyStringDouble();
		collection.add(strategy);
		try {
			collection.getFor(Integer.class, Math.class);
			fail();
		} catch (NoSuchElementException exp) {
			assertTrue(true);
		}
	}

	public void testSize() {
		assertEquals(0, collection.size());

		ImpactStrategy strategy = getImpactStrategyStringDouble();
		collection.add(strategy);
		assertEquals(1, collection.size());

		collection.remove(strategy);
		assertEquals(0, collection.size());
	}

	private ImpactStrategy getImpactStrategyStringDouble() {
		return new ImpactStrategy() {

			public Class getLeftObjectClass() {
				return String.class;
			}

			public Class getRightObjectClass() {
				return Double.class;
			}

			public void impact(Object leftObject, Object rightObject) {
			}
		};
	}
}
