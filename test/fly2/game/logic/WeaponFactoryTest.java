package fly2.game.logic;

import static fly2.common.Direction.*;
import junit.framework.TestCase;

public class WeaponFactoryTest extends TestCase {

	private WeaponFactory weaponFactory;

	public void setUp() {
		weaponFactory = WeaponFactory.getInstance();
	}

	public void testMakeGun() {
		Weapon gun = weaponFactory.makeGun();
		assertEquals(0.0, gun.getX(), 0.001);
		assertEquals(0.0, gun.getY(), 0.001);
		assertEquals(1, gun.getBulletDamage());
		assertEquals(0.5, gun.getBulletSize(), 0.001);
		assertEquals(1.0, gun.getBulletSpeed(), 0.001);
		assertEquals(_UNDEFINED, gun.getBulletDirection());
	}
}
