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
		assertEquals(3.5, player.getWidth());
		assertEquals(3.0, player.getHeight());
		assertEquals(UP, player.getDirection());
		assertEquals(10, player.getHealth());
		Weapon weapon = player.getWeapon();
		assertNotNull(weapon);
		assertEquals(UP, weapon.getBulletDirection());
		assertEquals(1.75, weapon.getX());
		assertEquals(3.0, weapon.getY());
	}

	public void testMakeEnemy() {
		Plane enemy = planeFactory.makeEnemy();
		assertEquals(0.0, enemy.getX());
		assertEquals(0.0, enemy.getY());
		assertEquals(3.5, enemy.getWidth());
		assertEquals(3.0, enemy.getHeight());
		assertEquals(DOWN, enemy.getDirection());
		assertEquals(1, enemy.getHealth());
		Weapon weapon = enemy.getWeapon();
		assertNotNull(weapon);
		assertEquals(DOWN, weapon.getBulletDirection());
		assertEquals(1.75, weapon.getX());
		assertEquals(3.0, weapon.getY());
	}
}
