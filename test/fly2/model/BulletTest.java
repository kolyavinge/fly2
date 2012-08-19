package fly2.model;

import junit.framework.TestCase;

public class BulletTest extends TestCase {

	private double damage = 20.0;
	private Bullet bullet;

	public void setUp() {
		bullet = new Bullet();
		bullet.setDamage(damage);
	}

	public void testNew() {
		assertEquals(damage, bullet.getDamage());
	}

	public void testNegativeDamageValue() {
		try {
			bullet.setDamage(-1.0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
