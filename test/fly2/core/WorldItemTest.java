package fly2.core;

import fly2.common.*;
import fly2.model.*;
import junit.framework.TestCase;

public class WorldItemTest extends TestCase {

	private WorldItem worldItem;

	public void setUp() {
		worldItem = new WorldItem();
	}

	public void testNew() {
		Rectangle bounds = worldItem.getBounds();
		assertEquals(0.0, bounds.getLeftUpX());
		assertEquals(0.0, bounds.getLeftUpY());
		assertEquals(1.0, bounds.getWidth());
		assertEquals(1.0, bounds.getHeight());
	}

	public void testSetLeftUpPoint() {
		worldItem.setLeftUpX(10);
		worldItem.setLeftUpY(20);
		Rectangle bounds = worldItem.getBounds();
		assertEquals(10.0, bounds.getLeftUpX());
		assertEquals(20.0, bounds.getLeftUpY());
	}

	public void testSetNegativeLeftUpPoint() {
		try {
			worldItem.setLeftUpX(-10);
			fail();
		} catch (IllegalArgumentException exp) {
		}
		try {
			worldItem.setLeftUpY(-20);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testSetSize() {
		worldItem.setSize(20, 40);
		Rectangle bounds = worldItem.getBounds();
		assertEquals(20.0, bounds.getWidth());
		assertEquals(40.0, bounds.getHeight());
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
		setLeftUpPoint(10, 20);
		Rectangle after = worldItem.getBounds();
		after = Rectangle.copyFrom(after);
		double moveValue = 20.0;
		worldItem.moveX(moveValue);
		Rectangle before = worldItem.getBounds();
		assertEquals(after.getLeftUpX() + moveValue, before.getLeftUpX());
		assertEquals(after.getLeftDownX() + moveValue, before.getLeftDownX());
		assertEquals(after.getRightUpX() + moveValue, before.getRightUpX());
		assertEquals(after.getRightDownX() + moveValue, before.getRightDownX());
	}

	public void testMoveY() {
		setLeftUpPoint(10, 20);
		Rectangle after = worldItem.getBounds();
		after = Rectangle.copyFrom(after);
		double moveValue = 10.0;
		worldItem.moveY(moveValue);
		Rectangle before = worldItem.getBounds();
		assertEquals(after.getLeftUpY() + moveValue, before.getLeftUpY());
		assertEquals(after.getLeftDownY() + moveValue, before.getLeftDownY());
		assertEquals(after.getRightUpY() + moveValue, before.getRightUpY());
		assertEquals(after.getRightDownY() + moveValue, before.getRightDownY());
	}
	
	private void setLeftUpPoint(double x, double y) {
		worldItem.setLeftUpX(x);
		worldItem.setLeftUpY(y);
	}
}
