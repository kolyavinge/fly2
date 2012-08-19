package fly2.core;

import static fly2.common.Direction.DOWN;
import static fly2.common.Direction.LEFT;
import static fly2.common.Direction.RIGHT;
import static fly2.common.Direction.UP;
import static fly2.unittest.RectangleTestUtils.assertXShifted;
import static fly2.unittest.RectangleTestUtils.assertYShifted;
import fly2.common.Bounds;
import fly2.common.Rectangle;
import junit.framework.TestCase;

public class MoveableWorldItemTest extends TestCase {

	private MoveableWorldItem worldItem;
	private double speed = 10.0;

	public void setUp() {
		worldItem = new MoveableWorldItem();
		worldItem.setSpeed(speed);
	}

	public void testNew() {
		assertEquals(speed, worldItem.getSpeed());
	}

	public void testMoveLeft() {
		worldItem.setDirection(LEFT);
		Bounds after = getCopyOfBounds();
		worldItem.update();
		Bounds before = getCopyOfBounds();
		assertXShifted(-worldItem.getSpeed(), after, before);
	}

	public void testMoveRight() {
		worldItem.setDirection(RIGHT);
		Bounds after = getCopyOfBounds();
		worldItem.update();
		Bounds before = getCopyOfBounds();
		assertXShifted(worldItem.getSpeed(), after, before);
	}

	public void testMoveUp() {
		worldItem.setDirection(UP);
		Bounds after = getCopyOfBounds();
		worldItem.update();
		Bounds before = getCopyOfBounds();
		assertYShifted(-worldItem.getSpeed(), after, before);
	}

	public void testMoveDown() {
		worldItem.setDirection(DOWN);
		Bounds after = getCopyOfBounds();
		worldItem.update();
		Bounds before = getCopyOfBounds();
		assertYShifted(worldItem.getSpeed(), after, before);
	}

	public void testNegativeSpeedValue() {
		try {
			worldItem.setSpeed(-1.0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	private Bounds getCopyOfBounds() {
		Bounds bounds = worldItem.getBounds();
		return Rectangle.copyFrom((Rectangle) bounds);
	}
}
