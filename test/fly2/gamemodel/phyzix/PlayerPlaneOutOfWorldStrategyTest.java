package fly2.gamemodel.phyzix;

import fly2.gamemodel.PlayerCompleteLevelCallback;
import fly2.gamemodel.phyzix.PlaneWorldItem;
import fly2.gamemodel.phyzix.PlayerPlaneOutOfWorldStrategy;
import fly2.unittest.BlankWeapon;
import junit.framework.TestCase;

public class PlayerPlaneOutOfWorldStrategyTest extends TestCase {

	private PlayerPlaneOutOfWorldStrategy strategy;
	private PlaneWorldItem plane;
	private double worldWidth = 100.0;
	private double worldHeight = 50.0;
	private PlayerCompleteLevelCallback callback;
	private boolean executeCallbackFlag;

	public void setUp() {
		callback = getPlayerPlaneFinishLevelCallback();
		strategy = new PlayerPlaneOutOfWorldStrategy(callback);
		plane = new PlaneWorldItem(new BlankWeapon());
		plane.setHealth(1);
		executeCallbackFlag = false;
	}

	public void testOutFromLeft() {
		plane.setPosition(-10.0, 10.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(0.0, plane.getX(), 0.001);
		assertEquals(10.0, plane.getY(), 0.001);
		assertFalse(executeCallbackFlag);
	}

	public void testOutFromRight() {
		plane.setPosition(worldWidth + 10.0, 10.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(worldWidth - plane.getWidth(), plane.getX(), 0.001);
		assertEquals(10.0, plane.getY(), 0.001);
		assertFalse(executeCallbackFlag);
	}

	public void testOutFromDown() {
		plane.setPosition(10.0, -10.0);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(10.0, plane.getX(), 0.001);
		assertEquals(0.0, plane.getY(), 0.001);
		assertFalse(executeCallbackFlag);
	}

	public void testOutFromUp() {
		plane.setPosition(10.0, worldHeight);
		strategy.activate(plane, worldWidth, worldHeight);
		assertEquals(10.0, plane.getX(), 0.001);
		assertTrue(executeCallbackFlag);
	}

	public void testCallbackNotNull() {
		try {
			new PlayerPlaneOutOfWorldStrategy(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private PlayerCompleteLevelCallback getPlayerPlaneFinishLevelCallback() {
		return new PlayerCompleteLevelCallback() {
			public void execute() {
				executeCallbackFlag = true;
			}
		};
	}
}
