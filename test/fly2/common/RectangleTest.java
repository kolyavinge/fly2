package fly2.common;

import fly2.common.Rectangle;
import junit.framework.TestCase;

public class RectangleTest extends TestCase {

	private Rectangle rect;

	public void setUp() {
		rect = new Rectangle();
	}
	
	public void testNew() {
		assertLeftUp(0, 0);
		assertRightUp(1.0, 0);
		assertLeftDown(0, 1.0);
		assertRightDown(1.0, 1.0);
		assertMiddle(0.5, 0.5);
		assertSize(1.0, 1.0);
	}

	public void testSize() {
		setSize(10, 20);

		assertLeftUp(0, 0);
		assertRightUp(10, 0);
		assertLeftDown(0, 20);
		assertRightDown(10, 20);
		assertMiddle(5, 10);
		assertSize(10, 20);
	}

	public void testSetWrongSize() {
		try {
			setSize(0, 20);
		} catch (IllegalArgumentException exp) {
		}

		try {
			setSize(10, 0);
		} catch (IllegalArgumentException exp) {
		}

		try {
			setSize(-1, 20);
		} catch (IllegalArgumentException exp) {
		}

		try {
			setSize(10, -1);
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testSetLeftUp() {
		setLeftUp(5, 10);
		setSize(10, 20);

		assertLeftUp(5, 10);
		assertRightUp(15, 10);
		assertLeftDown(5, 30);
		assertRightDown(15, 30);
		assertMiddle(10, 20);
		assertSize(10, 20);
	}

	public void testConstructor() {
		rect = new Rectangle(10, 20, 30, 40);
		assertLeftUp(10, 20);
		assertRightUp(40, 20);
		assertLeftDown(10, 60);
		assertRightDown(40, 60);
		assertMiddle(25, 40);
		assertSize(30, 40);
	}

	public void testCopyFrom() {
		rect = new Rectangle(10, 20, 30, 40);
		Rectangle copyRect = Rectangle.copyFrom(rect);
		assertEquals(rect.getLeftUpX(), copyRect.getLeftUpX());
		assertEquals(rect.getLeftUpY(), copyRect.getLeftUpY());
		assertEquals(rect.getLeftDownX(), copyRect.getLeftDownX());
		assertEquals(rect.getLeftDownY(), copyRect.getLeftDownY());
		assertEquals(rect.getRightUpX(), copyRect.getRightUpX());
		assertEquals(rect.getRightUpY(), copyRect.getRightUpY());
		assertEquals(rect.getRightDownX(), copyRect.getRightDownX());
		assertEquals(rect.getRightDownY(), copyRect.getRightDownY());
	}

	private void assertLeftUp(double x, double y) {
		assertEquals(x, rect.getLeftUpX());
		assertEquals(y, rect.getLeftUpY());
	}

	private void assertRightUp(double x, double y) {
		assertEquals(x, rect.getRightUpX());
		assertEquals(y, rect.getRightUpY());
	}

	private void assertLeftDown(double x, double y) {
		assertEquals(x, rect.getLeftDownX());
		assertEquals(y, rect.getLeftDownY());
	}

	private void assertRightDown(double x, double y) {
		assertEquals(x, rect.getRightDownX());
		assertEquals(y, rect.getRightDownY());
	}

	private void assertMiddle(double x, double y) {
		assertEquals(x, rect.getMiddleX());
		assertEquals(y, rect.getMiddleY());
	}

	private void assertSize(double w, double h) {
		assertEquals(w, rect.getWidth());
		assertEquals(h, rect.getHeight());
	}

	private void setSize(double w, double h) {
		rect.setWidth(w);
		rect.setHeight(h);
	}

	private void setLeftUp(double x, double y) {
		rect.setLeftUpX(x);
		rect.setLeftUpY(y);
	}
}
