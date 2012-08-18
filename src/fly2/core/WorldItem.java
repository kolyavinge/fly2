package fly2.core;

import fly2.common.Rectangle;

public class WorldItem {

	private Rectangle bounds;

	public WorldItem() {
		this(0, 0, 1, 1);
	}

	public WorldItem(double width, double height) {
		this(0, 0, width, height);
	}

	public WorldItem(double leftUpX, double leftUpY, double width, double height) {
		bounds = Rectangle.create(leftUpX, leftUpY, width, height);
	}

	public void setLeftUpX(double leftUpX) {
		if (leftUpX < 0)
			throw new IllegalArgumentException("Координата х не может быть отрицательной");

		bounds.setLeftUpX(leftUpX);
	}

	public void setLeftUpY(double leftUpY) {
		if (leftUpY < 0)
			throw new IllegalArgumentException("Координата y не может быть отрицательной");

		bounds.setLeftUpY(leftUpY);
	}

	public void moveX(double value) {
		bounds.setLeftUpX(bounds.getLeftUpX() + value);
	}

	public void moveY(double value) {
		bounds.setLeftUpY(bounds.getLeftUpY() + value);
	}

	public void setSize(double width, double height) {
		try {
			bounds.setWidth(width);
			bounds.setHeight(height);
		} catch (IllegalArgumentException exp) {
			throw new IllegalArgumentException("Размеры объекта заданы неверно");
		}
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
