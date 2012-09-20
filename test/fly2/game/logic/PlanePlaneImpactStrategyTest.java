package fly2.game.logic;

import junit.framework.TestCase;

public class PlanePlaneImpactStrategyTest extends TestCase {

	private PlanePlaneImpactStrategy impactStrategy;

	public void setUp() {
		impactStrategy = new PlanePlaneImpactStrategy();
	}

	public void testGetClasses() {
		assertEquals(Plane.class, impactStrategy.getFirstObjectClass());
		assertEquals(Plane.class, impactStrategy.getSecondObjectClass());
	}

	public void testActivateImpact() {
		Plane plane1 = getPlane();
		Plane plane2 = getPlane();

		assertFalse(plane1.isDestroyed());
		assertFalse(plane2.isDestroyed());

		impactStrategy.activateImpact(plane1, plane2);

		assertTrue(plane1.isDestroyed());
		assertTrue(plane2.isDestroyed());
	}

	private Plane getPlane() {
		Plane plane = new Plane();
		plane.setHealth(1000);

		return plane;
	}
}
