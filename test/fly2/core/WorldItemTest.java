package fly2.core;

import fly2.common.*;
import junit.framework.TestCase;
import static fly2.unittest.RectangleTestUtils.*;

public class WorldItemTest extends TestCase {

	private WorldItem worldItem;

	public void setUp() {
		worldItem = new WorldItem();
	}

	public void testNew() {
		Bounds bounds = worldItem.getBounds();
		assertEquals(0.0, bounds.getLeftUpX());
		assertEquals(0.0, bounds.getLeftUpY());
		assertEquals(1.0, worldItem.getWidth());
		assertEquals(1.0, worldItem.getHeight());
	}

	public void testSetLeftUpPoint() {
		worldItem.setLeftUpPoint(10, 20);
		Bounds bounds = worldItem.getBounds();
		assertEquals(10.0, bounds.getLeftUpX());
		assertEquals(20.0, bounds.getLeftUpY());
	}

	public void testSetLeftUpXYPoint() {
		worldItem.setLeftUpX(10);
		worldItem.setLeftUpY(20);
		Bounds bounds = worldItem.getBounds();
		assertEquals(10.0, bounds.getLeftUpX());
		assertEquals(20.0, bounds.getLeftUpY());
	}

	public void testSetNegativeLeftUpPoint() {
		worldItem.setLeftUpX(-10.0);
		worldItem.setLeftUpY(-20.0);
		Bounds bounds = worldItem.getBounds();
		assertEquals(-10.0, bounds.getLeftUpX());
		assertEquals(-20.0, bounds.getLeftUpY());
	}

	public void testSetWidthAndHeight() {
		worldItem.setWidth(20);
		worldItem.setHeight(40);
		assertEquals(20.0, worldItem.getWidth());
		assertEquals(40.0, worldItem.getHeight());
	}

	public void testSetSize() {
		worldItem.setSize(20, 40);
		assertEquals(20.0, worldItem.getWidth());
		assertEquals(40.0, worldItem.getHeight());
	}

	public void testSetWrongSize() {
		try {
			worldItem.setSize(0, 0);
			fail();
		} catch (IllegalArgumentException exp) {
		}

		try {
			worldItem.setSize(0, 1);
			fail();
		} catch (IllegalArgumentException exp) {
		}

		try {
			worldItem.setSize(1, 0);
			fail();
		} catch (IllegalArgumentException exp) {
		}

		try {
			worldItem.setSize(-1, 1);
			fail();
		} catch (IllegalArgumentException exp) {
		}

		try {
			worldItem.setSize(1, -1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testMoveX() {
		worldItem.setLeftUpPoint(10, 20);
		Bounds after = getCopyOfBounds();
		double moveValue = 20.0;
		worldItem.moveX(moveValue);
		Bounds before = getCopyOfBounds();
		assertXShifted(moveValue, after, before);
	}

	public void testMoveY() {
		worldItem.setLeftUpPoint(10, 20);
		Bounds after = getCopyOfBounds();
		double moveValue = 10.0;
		worldItem.moveY(moveValue);
		Bounds before = getCopyOfBounds();
		assertYShifted(moveValue, after, before);
	}

	private Bounds getCopyOfBounds() {
		Bounds bounds = worldItem.getBounds();
		return Rectangle.copyFrom((Rectangle) bounds);
	}
}
