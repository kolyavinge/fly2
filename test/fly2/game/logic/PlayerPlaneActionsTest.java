package fly2.game.logic;

import fly2.game.logic.PlayerPlaneActions;
import junit.framework.TestCase;

public class PlayerPlaneActionsTest extends TestCase {

	private GameWorld gameWorld;
	private Plane plane;
	private PlayerPlaneActions actions;

	public void setUp() {
		gameWorld = new GameWorld(100, 100);
		plane = gameWorld.getPlayerPlane();
		actions = new PlayerPlaneActions(gameWorld, plane);
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

	public void testMoveLeftOutOfWorld() {
		plane.setPosition(0, 20.0);
		actions.moveLeft();
		assertEquals(0.0, plane.getX(), 0.001);
		assertEquals(20.0, plane.getY(), 0.001);
	}

	public void testMoveRightOutOfWorld() {
		plane.setPosition(gameWorld.getWidth() - plane.getWidth(), 20.0);
		actions.moveRight();
		assertEquals(gameWorld.getWidth() - plane.getWidth(), plane.getX(), 0.001);
		assertEquals(20.0, plane.getY(), 0.001);
	}
	
	public void testGameWorldPlane() {
		try {
			new PlayerPlaneActions(null, new Plane());
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testNullPlane() {
		try {
			new PlayerPlaneActions(gameWorld, null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
