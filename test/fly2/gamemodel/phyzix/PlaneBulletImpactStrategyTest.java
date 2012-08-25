package fly2.gamemodel.phyzix;

import fly2.gamemodel.phyzix.BulletWorldItem;
import fly2.gamemodel.phyzix.DefaultWeapon;
import fly2.gamemodel.phyzix.PlaneBulletImpactStrategy;
import fly2.gamemodel.phyzix.PlaneWorldItem;
import fly2.unittest.*;
import junit.framework.TestCase;

public class PlaneBulletImpactStrategyTest extends TestCase {

	PlaneBulletImpactStrategy impactStrategy;

	public void setUp() {
		impactStrategy = new PlaneBulletImpactStrategy();
	}

	public void testGetClasses() {
		assertEquals(PlaneWorldItem.class, impactStrategy.getFirstObjectClass());
		assertEquals(BulletWorldItem.class, impactStrategy.getSecondObjectClass());
	}

	public void testActivateImpact() {
		PlaneWorldItem plane = getPlaneWithHealth(10);
		BulletWorldItem bullet = getBulletWithDamage(2);

		impactStrategy.activateImpact(plane, bullet);

		assertEquals(8, plane.getHealth());
		assertTrue(bullet.isDestroyed());
	}

	private PlaneWorldItem getPlaneWithHealth(int health) {
		DefaultWeapon weapon = new DefaultWeapon(new TestWorldItemCollection());
		PlaneWorldItem plane = new PlaneWorldItem(weapon);
		plane.setHealth(health);

		return plane;
	}

	private BulletWorldItem getBulletWithDamage(int damage) {
		BulletWorldItem bullet = new BulletWorldItem();
		bullet.setDamage(damage);

		return bullet;
	}
}
