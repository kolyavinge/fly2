package fly2.game.logic;

import fly2.game.logic.Bullet;
import junit.framework.TestCase;

public class BulletTest extends TestCase {

	private int damage = 20;
	private Bullet bullet;
	private int ownerPlaneId;

	public void setUp() {
		bullet = new Bullet();
		bullet.setDamage(damage);
		bullet.setOwnerPlaneId(ownerPlaneId);
	}

	public void testNew() {
		bullet = new Bullet();
		assertEquals(0, bullet.getDamage());
		assertFalse(bullet.isDestroyed());
		assertEquals(0, bullet.getOwnerPlaneId());
	}
	
	public void testGettersSetters() {
		assertEquals(damage, bullet.getDamage());
		assertFalse(bullet.isDestroyed());
		assertEquals(ownerPlaneId, bullet.getOwnerPlaneId());
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
