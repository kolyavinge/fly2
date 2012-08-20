package fly2.model;

import junit.framework.TestCase;

public class BulletTest extends TestCase {

	private int damage = 20;
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
			bullet.setDamage(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
