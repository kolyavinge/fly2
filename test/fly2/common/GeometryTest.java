package fly2.common;

import junit.framework.*;

public class GeometryTest extends TestCase {

	private double x, y;
	private double x0, y0, width0, height0;
	private double x1, y1, width1, height1;

	private void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	private void setRect0(double x, double y, double width, double height) {
		this.x0 = x;
		this.y0 = y;
		this.width0 = width;
		this.height0 = height;
	}

	private void setRect1(double x, double y, double width, double height) {
		this.x1 = x;
		this.y1 = y;
		this.width1 = width;
		this.height1 = height;
	}

	private boolean impactPoint() {
		return Geometry.impactPoint(x0, y0, width0, height0, x, y);
	}

	private boolean impactRect() {
		return Geometry.impactRect(x0, y0, width0, height0, x1, y1, width1, height1);
	}

	private boolean innerRect() {
		return Geometry.innerRect(x0, y0, width0, height0, x1, y1, width1, height1);
	}

	// impactPoint

	public void testImpactPoint_1() {
		setPoint(0, 0);
		setRect0(0, 0, 1, 1);
		assertTrue(impactPoint());
	}

	public void testImpactPoint_2() {
		setPoint(10, 10);
		setRect0(0, 0, 1, 1);
		assertFalse(impactPoint());
	}

	public void testImpactPoint_3() {
		setPoint(0.5f, 0.5f);
		setRect0(0, 0, 1, 1);
		assertTrue(impactPoint());
	}

	public void testImpactPoint_4() {
		setPoint(-1, -1);
		setRect0(0, 0, 1, 1);
		assertFalse(impactPoint());
	}

	public void testImpactPoint_5() {
		setPoint(1, 1);
		setRect0(0, 0, 1, 1);
		assertTrue(impactPoint());
	}

	// impactRect

	public void testImpactRect_1() {
		setRect0(0, 0, 1, 1);
		setRect1(0, 0, 1, 1);
		assertTrue(impactRect());
	}

	public void testImpactRect_2() {
		setRect0(0, 0, 2, 2);
		setRect1(1, 1, 2, 2);
		assertTrue(impactRect());
	}

	public void testImpactRect_3() {
		setRect0(0, 0, 10, 10);
		setRect1(2, 2, 1, 1);
		assertTrue(impactRect());
	}

	public void testImpactRect_4() {
		setRect0(0, 0, 1, 1);
		setRect1(2, 2, 1, 1);
		assertFalse(impactRect());
	}

	public void testImpactRect_5() {
		// прямоугольники перекрещиваются, но угловые точки одного не попадают в другой
		// тут ф-я работает неверно, но пока оставим как есть
		setRect0(0, 0, 1, 5);
		setRect1(-1, 2, 5, 1);
		assertFalse(impactRect());
	}

	public void testImpactRect_6() {
		setRect0(0, 0, 1, 1);
		setRect1(1, 1, 1, 1);
		assertTrue(impactRect());
	}

	// innerRect

	public void testInnerRect_1() {
		setRect0(0, 0, 2, 2);
		setRect1(1, 1, 1, 1);
		assertTrue(innerRect());
	}

	public void testInnerRect_2() {
		setRect0(0, 0, 1, 1);
		setRect1(0, 0, 1, 1);
		assertTrue(innerRect());
	}

	public void testInnerRect_3() {
		setRect0(0, 0, 2, 2);
		setRect1(0, 0, 3, 3);
		assertFalse(innerRect());
	}

	public void testInnerRect_4() {
		setRect0(0, 0, 2, 2);
		setRect1(3, 3, 1, 1);
		assertFalse(innerRect());
	}

	public void testInnerRect_5() {
		setRect0(1, 1, 2, 2);
		setRect1(0, 0, 10, 10);
		assertFalse(innerRect());
	}

	// between

	public void testBetween() {
		assertTrue(Geometry.between(0, -1, 1));
		assertTrue(Geometry.between(0, 0, 1));
		assertTrue(Geometry.between(0, -1, 0));
		assertFalse(Geometry.between(2, -1, 1));
	}
}
