package fly2.unittest;

import fly2.common.*;
import static junit.framework.Assert.*;

public class RectangleTestUtils {

	public static void assertXShifted(double speed, Bounds after, Bounds before) {
		assertEquals(after.getLeftUpX() + speed, before.getLeftUpX());
		assertEquals(after.getLeftDownX() + speed, before.getLeftDownX());
		assertEquals(after.getRightUpX() + speed, before.getRightUpX());
		assertEquals(after.getRightDownX() + speed, before.getRightDownX());
		assertEquals(after.getMiddleX() + speed, before.getMiddleX());
	}

	public static void assertYShifted(double speed, Bounds after, Bounds before) {
		assertEquals(after.getLeftUpY() + speed, before.getLeftUpY());
		assertEquals(after.getLeftDownY() + speed, before.getLeftDownY());
		assertEquals(after.getRightUpY() + speed, before.getRightUpY());
		assertEquals(after.getRightDownY() + speed, before.getRightDownY());
		assertEquals(after.getMiddleY() + speed, before.getMiddleY());
	}
}
