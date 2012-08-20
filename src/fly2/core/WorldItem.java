package fly2.core;

import fly2.common.Bounds;
import fly2.common.Rectangle;

/**
 * Объект игрового мира. Имеет координаты и размеры. Умеет перемещаться по осям Х и У.
 */
public class WorldItem {

	private Rectangle bounds;

	public WorldItem() {
		bounds = new Rectangle();
	}

	public void setLeftUpX(double leftUpX) {
		bounds.setLeftUpX(leftUpX);
	}

	public void setLeftUpY(double leftUpY) {
		bounds.setLeftUpY(leftUpY);
	}

	public void setLeftUpPoint(double x, double y) {
		setLeftUpX(x);
		setLeftUpY(y);
	}

	public void moveX(double value) {
		bounds.setLeftUpX(bounds.getLeftUpX() + value);
	}

	public void moveY(double value) {
		bounds.setLeftUpY(bounds.getLeftUpY() + value);
	}

	public double getWidth() {
		return bounds.getWidth();
	}

	public void setWidth(double width) {
		try {
			bounds.setWidth(width);
		} catch (IllegalArgumentException exp) {
			throw new IllegalArgumentException("Ширина объекта задана неверно");
		}
	}

	public double getHeight() {
		return bounds.getHeight();
	}

	public void setHeight(double height) {
		try {
			bounds.setHeight(height);
		} catch (IllegalArgumentException exp) {
			throw new IllegalArgumentException("Высота объекта задана неверно");
		}
	}

	public void setSize(double width, double height) {
		setWidth(width);
		setHeight(height);
	}

	public Bounds getBounds() {
		return bounds;
	}
}
