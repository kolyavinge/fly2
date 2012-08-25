package fly2.gamemodel.phyzix;

import fly2.common.*;
import fly2.gamemodel.phyzix.BulletWorldItem;
import fly2.gamemodel.phyzix.DefaultWeapon;
import fly2.phyzix.*;
import fly2.unittest.*;
import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class DefaultWeaponTest extends TestCase {

	private double x = 10.0, y = 20.0;
	private double bulletSize = 8.0;
	private Direction bulletDirection = UP;
	private int bulletDamage = 15;
	private double bulletSpeed = 50.0;
	private DefaultWeapon weapon;
	private WorldItemCollection worldItems;

	public void setUp() {
		worldItems = new TestWorldItemCollection();
		weapon = new DefaultWeapon(worldItems);
		weapon.setPosition(x, y);
		weapon.setBulletSize(bulletSize);
		weapon.setBulletDirection(bulletDirection);
		weapon.setBulletSpeed(bulletSpeed);
		weapon.setBulletDamage(bulletDamage);
	}

	public void testGettersSetters() {
		assertEquals(x, weapon.getX());
		assertEquals(y, weapon.getY());
		assertEquals(bulletSize, weapon.getBulletSize());
		assertEquals(bulletDirection, weapon.getBulletDirection());
		assertEquals(bulletDamage, weapon.getBulletDamage());
		assertEquals(bulletSpeed, weapon.getBulletSpeed());
		assertSame(worldItems, weapon.getWorldItems());
	}

	public void testNew() {
		weapon = new DefaultWeapon(worldItems);
		assertEquals(0.0, weapon.getX());
		assertEquals(0.0, weapon.getY());
		assertEquals(1.0, weapon.getBulletSize());
		assertEquals(LEFT, weapon.getBulletDirection());
		assertEquals(0, weapon.getBulletDamage());
		assertEquals(0.0, weapon.getBulletSpeed());
		assertSame(worldItems, weapon.getWorldItems());
	}

	public void testFire() {
		weapon.fire();
		assertEquals(1, worldItems.getItemsCount());
		WorldItem item = getFirstWorldItem();
		assertTrue(item instanceof BulletWorldItem);
		BulletWorldItem bullet = (BulletWorldItem) item;
		assertEquals(x, bullet.getMiddleX());
		assertEquals(y, bullet.getMiddleY());
		assertEquals(bulletSize, bullet.getWidth());
		assertEquals(bulletSize, bullet.getHeight());
		assertEquals(bulletDirection, bullet.getDirection());
		assertEquals(bulletSpeed, bullet.getSpeed());
		assertEquals(bulletDamage, bullet.getDamage());
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

	public void testSetNullWorldItemCollection() {
		try {
			new DefaultWeapon(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private WorldItem getFirstWorldItem() {
		return worldItems.getItems().iterator().next();
	}
}
