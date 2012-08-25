package fly2.model;

import fly2.unittest.TestWorldItemCollection;
import junit.framework.TestCase;

public class PlanePlaneImpactStrategyTest extends TestCase {

	private PlanePlaneImpactStrategy impactStrategy;

	public void setUp() {
		impactStrategy = new PlanePlaneImpactStrategy();
	}

	public void testGetClasses() {
		assertEquals(DefaultPlane.class, impactStrategy.getFirstObjectClass());
		assertEquals(DefaultPlane.class, impactStrategy.getSecondObjectClass());
	}

	public void testActivateImpact() {
		DefaultPlane plane1 = getPlane();
		DefaultPlane plane2 = getPlane();

		assertFalse(plane1.isDestroyed());
		assertFalse(plane2.isDestroyed());

		impactStrategy.activateImpact(plane1, plane2);

		assertTrue(plane1.isDestroyed());
		assertTrue(plane2.isDestroyed());
	}

	private DefaultPlane getPlane() {
		DefaultWeapon weapon = new DefaultWeapon(new TestWorldItemCollection());
		DefaultPlane plane = new DefaultPlane(weapon);
		plane.setHealth(1000);

		return plane;
	}
}
