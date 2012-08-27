package fly2.game.logic;

import fly2.phyzix.WorldItemCollection;
import fly2.unittest.TestWorldItemCollection;
import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class WeaponFactoryTest extends TestCase {

	private WeaponFactory weaponFactory;
	private WorldItemCollection worldItemCollection;

	public void setUp() {
		worldItemCollection = new TestWorldItemCollection();
		weaponFactory = new WeaponFactory(worldItemCollection);
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
