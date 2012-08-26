package fly2.game.logic;

import fly2.game.logic.Bullet;
import junit.framework.TestCase;

public class BulletTest extends TestCase {

	private int damage = 20;
	private Bullet bullet;

	public void setUp() {
		bullet = new Bullet();
		bullet.setDamage(damage);
	}

	public void testNew() {
		bullet = new Bullet();
		assertEquals(0, bullet.getDamage());
		assertFalse(bullet.isDestroyed());
	}
	
	public void testGettersSetters() {
		assertEquals(damage, bullet.getDamage());
		assertFalse(bullet.isDestroyed());
	}
	
	public void testDestroy() {
		assertFalse(bullet.isDestroyed());
		bullet.destroy();
		assertTrue(bullet.isDestroyed());
	}

	public void testNegativeDamageValue() {
		try {
			bullet.setDamage(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
