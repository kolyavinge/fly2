package fly2.game.logic;

import fly2.unittest.TestWorldItemCollection;
import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class PlaneFactoryTest extends TestCase {

	private PlaneFactory planeFactory;

	public void setUp() {
		WeaponFactory weaponFactory = new WeaponFactory(new TestWorldItemCollection());
		planeFactory = new PlaneFactory(weaponFactory);
	}

	public void testMakePlayer() {
		Plane player = planeFactory.makePlayer();
		assertEquals(0.0, player.getX());
		assertEquals(0.0, player.getY());
		assertEquals(2.0, player.getWidth());
		assertEquals(1.5, player.getHeight());
		assertEquals(UP, player.getDirection());
		assertEquals(10, player.getHealth());
		assertEquals(0.01, player.getSpeed());
		Weapon weapon = player.getWeapon();
		assertNotNull(weapon);
		assertEquals(UP, weapon.getBulletDirection());
		assertEquals(1.0, weapon.getX());
		assertEquals(1.5, weapon.getY());
	}

	public void testMakeEnemy() {
		Plane enemy = planeFactory.makeEnemy();
		assertEquals(0.0, enemy.getX());
		assertEquals(0.0, enemy.getY());
		assertEquals(2.0, enemy.getWidth());
		assertEquals(1.5, enemy.getHeight());
		assertEquals(DOWN, enemy.getDirection());
		assertEquals(1, enemy.getHealth());
		assertEquals(0.01, enemy.getSpeed());
		Weapon weapon = enemy.getWeapon();
		assertNotNull(weapon);
		assertEquals(DOWN, weapon.getBulletDirection());
		assertEquals(1.0, weapon.getX());
		assertEquals(1.5, weapon.getY());
	}
}
