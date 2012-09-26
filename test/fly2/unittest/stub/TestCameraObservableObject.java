package fly2.unittest.stub;

import fly2.game.view.CameraObservableObject;

public class TestCameraObservableObject implements CameraObservableObject {

	private double width, height;
	private double x, y;

	public double getWidth() {
		return width;
	}

	public TestCameraObservableObject setWidth(double width) {
		this.width = width;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public TestCameraObservableObject setHeight(double height) {
		this.height = height;
		return this;
	}

	public double getX() {
		return x;
	}

	public TestCameraObservableObject setX(double x) {
		this.x = x;
		return this;
	}

	public double getY() {
		return y;
	}

	public TestCameraObservableObject setY(double y) {
		this.y = y;
		return this;
	}
}
