package fly2.unittest;

import fly2.game.frontend.Plane;
import fly2.game.frontend.PlaneKind;
import fly2.game.frontend.Weapon;

public class TestPlane implements Plane {

	private double width, height;
	private double x, y;
	
	public PlaneKind getKind() {
		return PlaneKind._UNDEFINED;
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

	public double getMiddleX() {
		throw new UnsupportedOperationException();
	}

	public double getMiddleY() {
		throw new UnsupportedOperationException();
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getHealth() {
		throw new UnsupportedOperationException();
	}

	public Weapon getWeapon() {
		throw new UnsupportedOperationException();
	}

	public boolean isDestroyed() {
		throw new UnsupportedOperationException();
	}
}