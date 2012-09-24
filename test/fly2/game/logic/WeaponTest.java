package fly2.game.logic;

import static fly2.game.frontend.Direction.UP;
import static fly2.game.frontend.Direction._UNDEFINED;
import fly2.game.frontend.Direction;
import junit.framework.TestCase;

public class WeaponTest extends TestCase {

	private double x = 10.0, y = 20.0;
	private double bulletSize = 8.0;
	private Direction bulletDirection = UP;
	private int bulletDamage = 15;
	private double bulletSpeed = 50.0;
	private int ownerPlaneId = 123;
	private Weapon weapon;

	public void setUp() {
		weapon = new Weapon();
		weapon.setPosition(x, y);
		weapon.setBulletSize(bulletSize);
		weapon.setBulletDirection(bulletDirection);
		weapon.setBulletSpeed(bulletSpeed);
		weapon.setBulletDamage(bulletDamage);
		weapon.setOwnerPlaneId(ownerPlaneId);
	}

	public void testGettersSetters() {
		assertEquals(x, weapon.getX());
		assertEquals(y, weapon.getY());
		assertEquals(bulletSize, weapon.getBulletSize());
		assertEquals(bulletDirection, weapon.getBulletDirection());
		assertEquals(bulletDamage, weapon.getBulletDamage());
		assertEquals(bulletSpeed, weapon.getBulletSpeed());
		assertSame(ownerPlaneId, weapon.getOwnerPlaneId());
	}

	public void testNew() {
		weapon = new Weapon();
		assertEquals(0.0, weapon.getX());
		assertEquals(0.0, weapon.getY());
		assertEquals(1.0, weapon.getBulletSize());
		assertEquals(_UNDEFINED, weapon.getBulletDirection());
		assertEquals(0, weapon.getBulletDamage());
		assertEquals(0.0, weapon.getBulletSpeed());
	}

	public void testFire() {
		Bullet bullet = weapon.fire();
		assertEquals(x, bullet.getMiddleX());
		assertEquals(y, bullet.getMiddleY());
		assertEquals(bulletSize, bullet.getWidth());
		assertEquals(bulletSize, bullet.getHeight());
		assertEquals(bulletDirection, bullet.getFlyDirection());
		assertEquals(bulletSpeed, bullet.getFlySpeed());
		assertEquals(bulletDamage, bullet.getDamage());
		assertEquals(ownerPlaneId, bullet.getOwnerPlaneId());
	}

	public void moveX() {
		weapon.setPosition(1.0, 1.0);
		weapon.moveX(2.0);
		assertEquals(3.0, weapon.getX(), 0.001);
		assertEquals(1.0, weapon.getY(), 0.001);
	}

	public void moveY() {
		weapon.setPosition(1.0, 1.0);
		weapon.moveY(2.0);
		assertEquals(1.0, weapon.getX(), 0.001);
		assertEquals(3.0, weapon.getY(), 0.001);
	}
}
