package fly2.phyzix;

/**
 * Объект игрового мира. Имеет координаты и размеры. Умеет перемещаться по осям Х и У.
 */
public class WorldItem {

	private double x, y;
	private double width, height;
	private boolean destroyed;

	public WorldItem() {
		x = y = 0.0;
		width = height = 1.0;
		destroyed = false;
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

	public final void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}

	public final double getMiddleX() {
		return getX() + (getWidth() / 2.0);
	}

	public final double getMiddleY() {
		return getY() + (getHeight() / 2.0);
	}

	protected void moveX(double value) {
		x += value;
	}

	protected void moveY(double value) {
		y += value;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void destroy() {
		destroyed = true;
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

	public final void setSize(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
}
