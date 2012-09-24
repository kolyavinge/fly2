package fly2.phyzix.ext;

import fly2.phyzix.ext.FlyingWorldItem;
import junit.framework.TestCase;
import static fly2.game.frontend.Direction.*;

public class FlyingWorldItemTest extends TestCase {

	private FlyingWorldItem worldItem;
	private double speed = 10.0;

	public void setUp() {
		worldItem = new FlyingWorldItem();
		worldItem.setFlySpeed(speed);
	}

	public void testNew() {
		worldItem = new FlyingWorldItem();
		assertEquals(0.0, worldItem.getFlySpeed());
		assertEquals(_UNDEFINED, worldItem.getFlyDirection());
	}

	public void testGettersSetters() {
		assertEquals(speed, worldItem.getFlySpeed());
	}

	public void testMoveLeft() {
		worldItem.setPosition(0, 0);
		worldItem.setFlyDirection(LEFT);
		worldItem.update();
		assertEquals(-worldItem.getFlySpeed(), worldItem.getX(), 0.001);
		assertEquals(0.0, worldItem.getY(), 0.001);
	}

	public void testMoveRight() {
		worldItem.setPosition(0, 0);
		worldItem.setFlyDirection(RIGHT);
		worldItem.update();
		assertEquals(worldItem.getFlySpeed(), worldItem.getX(), 0.001);
		assertEquals(0.0, worldItem.getY(), 0.001);
	}

	public void testMoveUp() {
		worldItem.setPosition(0, 0);
		worldItem.setFlyDirection(UP);
		worldItem.update();
		assertEquals(0.0, worldItem.getX(), 0.001);
		assertEquals(worldItem.getFlySpeed(), worldItem.getY(), 0.001);
	}

	public void testMoveDown() {
		worldItem.setPosition(0, 0);
		worldItem.setFlyDirection(DOWN);
		worldItem.update();
		assertEquals(0.0, worldItem.getX(), 0.001);
		assertEquals(-worldItem.getFlySpeed(), worldItem.getY(), 0.001);
	}

	public void testMoveUndefined() {
		worldItem.setPosition(0, 0);
		try {
			worldItem.update();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testNullDirection() {
		try {
			worldItem.setFlyDirection(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testNegativeSpeedValue() {
		try {
			worldItem.setFlySpeed(-1.0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
