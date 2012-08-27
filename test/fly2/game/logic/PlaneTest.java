package fly2.game.logic;

import fly2.game.logic.Bullet;
import fly2.game.logic.Weapon;
import fly2.game.logic.Plane;
import fly2.phyzix.*;
import fly2.unittest.*;
import junit.framework.TestCase;

public class PlaneTest extends TestCase {

	private double width = 10.0;
	private double height = 20.0;
	private int health = 15;
	private Plane plane;
	private Weapon weapon;
	private WorldItemCollection worldItems;

	public void setUp() {
		worldItems = new TestWorldItemCollection();
		weapon = new Weapon(worldItems);
		plane = new Plane(weapon);
		plane.setSize(width, height);
		plane.setHealth(health);
	}

	public void testNew() {
		plane = new Plane(weapon);
		assertEquals(0, plane.getHealth());
		assertTrue(plane.isDestroyed());
		assertSame(weapon, plane.getWeapon());
	}

	public void testSettersGetters() {
		assertEquals(health, plane.getHealth());
		assertFalse(plane.isDestroyed());
		assertSame(weapon, plane.getWeapon());
	}

	public void testFire() {
		plane.setWeapon(weapon);
		plane.fire();
		assertEquals(1, worldItems.getItemsCount());
		WorldItem item = getFirstItem(worldItems);
		assertTrue(item instanceof Bullet);
		// убеждаемся что пулька создалась, все остальное
		// протестировано в классе Weapon
	}

	public void testSetPosition() {
		plane.setPosition(0.0, 0.0);
		weapon.setPosition(0.5, 1.0);

		plane.setPosition(5.0, 8.0);

		assertEquals(5.5, weapon.getX(), 0.001);
		assertEquals(9.0, weapon.getY(), 0.001);
	}

	public void testMoveWithWeaponRight() {
		weapon.setPosition(1.0, 1.0);
		plane.moveX(2.0);
		assertEquals(3.0, weapon.getX(), 0.001);
		assertEquals(1.0, weapon.getY(), 0.001);
	}

	public void testMoveWithWeaponLeft() {
		weapon.setPosition(10.0, 1.0);
		plane.moveX(-2.0);
		assertEquals(8.0, weapon.getX(), 0.001);
		assertEquals(1.0, weapon.getY(), 0.001);
	}

	public void testMoveWithWeaponUp() {
		weapon.setPosition(1.0, 1.0);
		plane.moveY(2.0);
		assertEquals(1.0, weapon.getX(), 0.001);
		assertEquals(3.0, weapon.getY(), 0.001);
	}

	public void testMoveWithWeaponDown() {
		weapon.setPosition(1.0, 10.0);
		plane.moveY(-2.0);
		assertEquals(1.0, weapon.getX(), 0.001);
		assertEquals(8.0, weapon.getY(), 0.001);
	}

	public void testDamage() {
		assertEquals(health, plane.getHealth());
		plane.damage(2);
		assertEquals(health - 2, plane.getHealth());
	}

	public void testTotalDamage() {
		assertEquals(health, plane.getHealth());
		plane.damage(health);
		assertEquals(0, plane.getHealth());
	}

	public void testMoreTotalDamage() {
		assertEquals(health, plane.getHealth());
		plane.damage(2 * health);
		assertEquals(0, plane.getHealth());
	}

	public void testDestroy() {
		assertFalse(plane.isDestroyed());
		plane.destroy();
		assertTrue(plane.isDestroyed());
	}

	public void testZeroDamage() {
		assertEquals(health, plane.getHealth());
		plane.damage(0);
		assertEquals(health, plane.getHealth());
	}

	public void testNegativeDamage() {
		try {
			plane.damage(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testZeroHealth() {
		plane.setHealth(0);
		assertEquals(0, plane.getHealth());
	}

	public void testNegativeHealth() {
		try {
			plane.setHealth(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testIsDestroyed() {
		assertFalse(plane.isDestroyed());
		plane.damage(plane.getHealth());
		assertTrue(plane.isDestroyed());
	}
	
	public void testSetOwnerIdToWeapon() {
		plane.setWeapon(weapon);
		assertEquals(plane.getId(), weapon.getOwnerPlaneId());
	}

	public void testCreateWithNullWeapon() {
		try {
			plane.setWeapon(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullWeapon() {
		try {
			plane = new Plane(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testFireWithNullWeapon() {
		plane = new Plane();
		try {
			plane.fire();
		} catch (IllegalStateException exp) {
		}
	}

	private WorldItem getFirstItem(WorldItemCollection itemCollection) {
		return itemCollection.getItems().iterator().next();
	}
}
