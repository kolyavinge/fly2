package fly2.game.logic;

import static fly2.game.frontend.PlaneKind.*;
import fly2.unittest.stub.TestPlaneListener;
import junit.framework.TestCase;

public class PlaneTest extends TestCase {

	private double width = 10.0;
	private double height = 20.0;
	private int health = 15;
	private double moveSpeed = 12.0;
	private Plane plane;
	private Weapon weapon;
	private TestPlaneListener planeListener;

	public void setUp() {
		planeListener = new TestPlaneListener();
		weapon = new Weapon();
		plane = new Plane();
		plane.setKind(PLAYER);
		plane.setSize(width, height);
		plane.setHealth(health);
		plane.setMoveSpeed(moveSpeed);
		plane.setListener(planeListener);
		plane.setWeapon(weapon);
	}

	public void testNew() {
		plane = new Plane();
		assertEquals(_UNDEFINED, plane.getKind());
		assertEquals(0, plane.getHealth());
		assertEquals(0, plane.getMoveSpeed(), 0.001);
		assertTrue(plane.isDestroyed());
		assertNotNull(plane.getWeapon());
		assertTrue(plane.getWeapon() instanceof NullWeapon);
		assertNotNull(plane.getListener());
		assertTrue(plane.getListener() instanceof DefaultPlaneListener);
	}

	public void testSettersGetters() {
		assertEquals(PLAYER, plane.getKind());
		assertEquals(health, plane.getHealth());
		assertEquals(moveSpeed, plane.getMoveSpeed(), 0.001);
		assertFalse(plane.isDestroyed());
		assertSame(weapon, plane.getWeapon());
		assertSame(planeListener, plane.getListener());
	}

	public void testFire() {
		plane.setWeapon(weapon);
		plane.fire();
		assertTrue(planeListener.onFireCall);
		assertNotNull(planeListener.plane);
		assertNotNull(planeListener.bullet);
		assertSame(plane, planeListener.plane);
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
	
	public void testMoveLeft() {
		weapon.setPosition(0, 10.0);
		plane.moveLeft();
		assertEquals(-plane.getMoveSpeed(), plane.getX(), 0.001);
	}
	
	public void testMoveRight() {
		weapon.setPosition(0, 10.0);
		plane.moveRight();
		assertEquals(plane.getMoveSpeed(), plane.getX(), 0.001);
	}

	public void testRaiseOnMoveLeftEvent() {
		assertFalse(planeListener.onMoveLeftCall);
		plane.moveLeft();
		assertTrue(planeListener.onMoveLeftCall);
		assertSame(plane, planeListener.plane);
	}

	public void testRaiseOnMoveRightEvent() {
		assertFalse(planeListener.onMoveRightCall);
		plane.moveRight();
		assertTrue(planeListener.onMoveRightCall);
		assertSame(plane, planeListener.plane);
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

	public void testIsDestroyed() {
		assertFalse(plane.isDestroyed());
		plane.damage(plane.getHealth());
		assertTrue(plane.isDestroyed());
	}

	public void testSetOwnerIdToWeapon() {
		plane.setWeapon(weapon);
		assertEquals(plane.getId(), weapon.getOwnerPlaneId());
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

	public void testNegativeMoveSpeed() {
		try {
			plane.setMoveSpeed(-1.0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testCreateWithNullWeapon() {
		try {
			plane.setWeapon(null);
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

	public void testNullListener() {
		try {
			plane.setListener(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
