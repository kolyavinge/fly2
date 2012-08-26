package fly2.game.logic;

import fly2.game.logic.BulletWorldItem;
import junit.framework.TestCase;

public class BulletWorldItemTest extends TestCase {

	private int damage = 20;
	private BulletWorldItem bullet;

	public void setUp() {
		bullet = new BulletWorldItem();
		bullet.setDamage(damage);
	}

	public void testNew() {
		bullet = new BulletWorldItem();
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
