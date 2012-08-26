package fly2.game.logic;

import fly2.game.logic.Weapon;
import fly2.game.logic.PlanePlaneImpactStrategy;
import fly2.game.logic.PlaneWorldItem;
import fly2.unittest.TestWorldItemCollection;
import junit.framework.TestCase;

public class PlanePlaneImpactStrategyTest extends TestCase {

	private PlanePlaneImpactStrategy impactStrategy;

	public void setUp() {
		impactStrategy = new PlanePlaneImpactStrategy();
	}

	public void testGetClasses() {
		assertEquals(PlaneWorldItem.class, impactStrategy.getFirstObjectClass());
		assertEquals(PlaneWorldItem.class, impactStrategy.getSecondObjectClass());
	}

	public void testActivateImpact() {
		PlaneWorldItem plane1 = getPlane();
		PlaneWorldItem plane2 = getPlane();

		assertFalse(plane1.isDestroyed());
		assertFalse(plane2.isDestroyed());

		impactStrategy.activateImpact(plane1, plane2);

		assertTrue(plane1.isDestroyed());
		assertTrue(plane2.isDestroyed());
	}

	private PlaneWorldItem getPlane() {
		Weapon weapon = new Weapon(new TestWorldItemCollection());
		PlaneWorldItem plane = new PlaneWorldItem(weapon);
		plane.setHealth(1000);

		return plane;
	}
}
