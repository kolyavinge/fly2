package fly2.game.logic;

import junit.framework.TestCase;

public class PlaneActionsTest extends TestCase {

	private Plane plane;
	private PlaneActions actions;

	public void setUp() {
		plane = new Plane();
		plane.setWeapon(new Weapon());
		actions = new PlaneActions(plane);
	}

	public void testMoveLeft() {
		plane.setPosition(10.0, 20.0);
		actions.moveLeft();
		assertEquals(-plane.getMoveSpeed(), plane.getX() - 10.0, 0.001);
		assertEquals(20.0, plane.getY(), 0.001);
	}

	public void testMoveRight() {
		plane.setPosition(10.0, 20.0);
		actions.moveRight();
		assertEquals(plane.getMoveSpeed(), plane.getX() - 10.0, 0.001);
		assertEquals(20.0, plane.getY(), 0.001);
	}

	public void testNullPlane() {
		try {
			new PlaneActions(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
