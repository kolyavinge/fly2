package fly2.game.logic;

import fly2.game.logic.PlayerPlaneActions;
import junit.framework.TestCase;

public class PlayerPlaneActionsTest extends TestCase {

	private Plane plane;
	private PlayerPlaneActions actions;

	public void setUp() {
		plane = new Plane();
		actions = new PlayerPlaneActions(plane);
	}

	public void testMoveLeft() {
		plane.setPosition(10.0, 20.0);
		actions.moveLeft();
		assertTrue(plane.getX() < 10.0);
		assertEquals(20.0, plane.getY(), 0.001);
	}

	public void testMoveRight() {
		plane.setPosition(10.0, 20.0);
		actions.moveRight();
		assertTrue(plane.getX() > 10.0);
		assertEquals(20.0, plane.getY(), 0.001);
	}

	public void testNullPlane() {
		try {
			new PlayerPlaneActions(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
