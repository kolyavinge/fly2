package fly2.phyzix.ext;

import fly2.phyzix.ext.MoveableWorldItem;
import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class MoveableWorldItemTest extends TestCase {

	private MoveableWorldItem worldItem;
	private double speed = 10.0;

	public void setUp() {
		worldItem = new MoveableWorldItem();
		worldItem.setSpeed(speed);
	}

	public void testGettersSetters() {
		assertEquals(speed, worldItem.getSpeed());
	}

	public void testMoveLeft() {
		worldItem.setPosition(0, 0);
		worldItem.setDirection(LEFT);
		worldItem.update();
		assertEquals(-worldItem.getSpeed(), worldItem.getX(), 0.001);
		assertEquals(0.0, worldItem.getY(), 0.001);
	}

	public void testMoveRight() {
		worldItem.setPosition(0, 0);
		worldItem.setDirection(RIGHT);
		worldItem.update();
		assertEquals(worldItem.getSpeed(), worldItem.getX(), 0.001);
		assertEquals(0.0, worldItem.getY(), 0.001);
	}

	public void testMoveUp() {
		worldItem.setPosition(0, 0);
		worldItem.setDirection(UP);
		worldItem.update();
		assertEquals(0.0, worldItem.getX(), 0.001);
		assertEquals(worldItem.getSpeed(), worldItem.getY(), 0.001);
	}

	public void testMoveDown() {
		worldItem.setPosition(0, 0);
		worldItem.setDirection(DOWN);
		worldItem.update();
		assertEquals(0.0, worldItem.getX(), 0.001);
		assertEquals(-worldItem.getSpeed(), worldItem.getY(), 0.001);
	}

	public void testNegativeSpeedValue() {
		try {
			worldItem.setSpeed(-1.0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
