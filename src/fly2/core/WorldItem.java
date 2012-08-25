package fly2.core;

/**
 * Объект игрового мира. Имеет координаты и размеры. Умеет перемещаться по осям Х и У.
 */
public class WorldItem {

	private double x, y;
	private double width, height;

	public WorldItem() {
		x = y = 0.0;
		width = height = 1.0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}

	public double getMiddleX() {
		return x + (width / 2.0);
	}

	public double getMiddleY() {
		return y + (height / 2.0);
	}

	public void moveX(double value) {
		x += value;
	}

	public void moveY(double value) {
		y += value;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		if (width <= 0.0)
			throw new IllegalArgumentException("Ширина объекта задана неверно");
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		if (height <= 0.0)
			throw new IllegalArgumentException("Высота объекта задана неверно");
		this.height = height;
	}

	public void setSize(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
}
