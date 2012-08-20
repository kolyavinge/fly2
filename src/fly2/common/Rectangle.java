package fly2.common;

public class Rectangle implements Bounds {

	public static Rectangle copyFrom(Rectangle rect) {
		Rectangle copyRect = new Rectangle();
		copyRect.leftUpX = rect.leftUpX;
		copyRect.leftUpY = rect.leftUpY;
		copyRect.width = rect.width;
		copyRect.height = rect.height;

		return copyRect;
	}

	private double leftUpX, leftUpY;
	private double width, height;

	public Rectangle() {
		this(0.0, 0.0, 1.0, 1.0);
	}
	
	public Rectangle(double leftUpX, double leftUpY, double width, double height) {
		setLeftUpX(leftUpX);
		setLeftUpY(leftUpY);
		setWidth(width);
		setHeight(height);
	}	

	public double getLeftUpX() {
		return leftUpX;
	}

	public void setLeftUpX(double leftUpX) {
		this.leftUpX = leftUpX;
	}

	public double getLeftUpY() {
		return leftUpY;
	}

	public void setLeftUpY(double leftUpY) {
		this.leftUpY = leftUpY;
	}

	public double getRightUpX() {
		return leftUpX + width;
	}

	public double getRightUpY() {
		return leftUpY;
	}

	public double getLeftDownX() {
		return leftUpX;
	}

	public double getLeftDownY() {
		return leftUpY + height;
	}

	public double getRightDownX() {
		return getRightUpX();
	}

	public double getRightDownY() {
		return getLeftDownY();
	}

	public double getMiddleX() {
		return leftUpX + width / 2.0;
	}

	public double getMiddleY() {
		return leftUpY + height / 2.0;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		if (width <= 0)
			throw new IllegalArgumentException("Ширина прямоугольника должна быть больше нуля");

		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		if (height <= 0)
			throw new IllegalArgumentException("Высота прямоугольника должна быть больше нуля");

		this.height = height;
	}
}
