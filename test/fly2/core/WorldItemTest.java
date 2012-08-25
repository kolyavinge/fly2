package fly2.core;

import junit.framework.TestCase;

public class WorldItemTest extends TestCase {

	private WorldItem worldItem;

	public void setUp() {
		worldItem = new WorldItem();
	}

	public void testNew() {
		worldItem = new WorldItem();
		assertEquals(0.0, worldItem.getX());
		assertEquals(0.0, worldItem.getY());
		assertEquals(1.0, worldItem.getWidth());
		assertEquals(1.0, worldItem.getHeight());
		assertEquals(0.5, worldItem.getMiddleX());
		assertEquals(0.5, worldItem.getMiddleY());
	}

	public void testSetXY() {
		worldItem.setX(10.0);
		assertEquals(10.0, worldItem.getX());
		worldItem.setY(20.0);
		assertEquals(20.0, worldItem.getY());
	}

	public void testSetPosition() {
		worldItem.setPosition(10.0, 20.0);
		assertEquals(10.0, worldItem.getX());
		assertEquals(20.0, worldItem.getY());
	}

	public void testSetNegativePosition() {
		worldItem.setPosition(-10.0, -20.0);
		assertEquals(-10.0, worldItem.getX());
		assertEquals(-20.0, worldItem.getY());
	}

	public void testMiddleXY() {
		worldItem.setPosition(2.0, 6.0);
		worldItem.setSize(10.0, 20.0);
		assertEquals(7.0, worldItem.getMiddleX(), 0.001);
		assertEquals(16.0, worldItem.getMiddleY(), 0.001);
	}

	public void testSetWidthAndHeight() {
		worldItem.setWidth(20.0);
		worldItem.setHeight(40.0);
		assertEquals(20.0, worldItem.getWidth());
		assertEquals(40.0, worldItem.getHeight());
	}

	public void testSetSize() {
		worldItem.setSize(20.0, 40.0);
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
		worldItem.setPosition(10.0, 20.0);
		double moveValue = 10.0;
		worldItem.moveX(moveValue);
		assertEquals(10.0 + moveValue, worldItem.getX(), 0.001);
		assertEquals(20.0, worldItem.getY(), 0.001);
	}

	public void testMoveY() {
		worldItem.setPosition(10.0, 20.0);
		double moveValue = 10.0;
		worldItem.moveY(moveValue);
		assertEquals(10.0, worldItem.getX(), 0.001);
		assertEquals(20.0 + moveValue, worldItem.getY(), 0.001);
	}
}
