package fly2.game.logic;

import junit.framework.TestCase;

public class PlaneBulletImpactStrategyTest extends TestCase {

	private PlaneBulletImpactStrategy impactStrategy;

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
	
	public void testOwnerBullet() {
		Plane plane = getPlaneWithHealth(10);
		Bullet bullet = getBulletWithDamage(2);
		bullet.setOwnerPlaneId(plane.getId());

		impactStrategy.activateImpact(plane, bullet);

		assertEquals(10, plane.getHealth());
		assertFalse(bullet.isDestroyed());
	}

	private Plane getPlaneWithHealth(int health) {
		Plane plane = new Plane();
		plane.setHealth(health);

		return plane;
	}

	private Bullet getBulletWithDamage(int damage) {
		Bullet bullet = new Bullet();
		bullet.setDamage(damage);

		return bullet;
	}
}
