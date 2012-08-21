package fly2.model;

import fly2.unittest.*;
import junit.framework.TestCase;

public class PlaneBulletImpactStrategyTest extends TestCase {

	PlaneBulletImpactStrategy impactStrategy;

	public void setUp() {
		impactStrategy = new PlaneBulletImpactStrategy();
	}

	public void testGetClasses() {
		assertEquals(Plane.class, impactStrategy.getFirstObjectClass());
		assertEquals(Bullet.class, impactStrategy.getSecondObjectClass());
	}

	public void testActivateImpact() {
		Plane plane = getPlaneWithHealth(10);
		Bullet bullet = getBulletWithDamage(2);

		impactStrategy.activateImpact(plane, bullet);

		assertEquals(8, plane.getHealth());
		assertTrue(bullet.isDestroyed());
	}

	private Plane getPlaneWithHealth(int health) {
		Weapon weapon = new Weapon(new TestWorldItemCollection());
		Plane plane = new Plane(weapon);
		plane.setHealth(health);

		return plane;
	}

	private Bullet getBulletWithDamage(int damage) {
		Bullet bullet = new Bullet();
		bullet.setDamage(damage);

		return bullet;
	}
}
