package fly2.unittest.stub;

import fly2.game.frontend.Bullet;
import fly2.game.frontend.Direction;

public class TestBullet implements Bullet {

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}

	public double getMiddleX() {
		return 0;
	}

	public double getMiddleY() {
		return 0;
	}

	public double getWidth() {
		return 0;
	}

	public double getHeight() {
		return 0;
	}

	public boolean isDestroyed() {
		return false;
	}

	public Direction getFlyDirection() {
		return null;
	}

	public int getDamage() {
		return 0;
	}

	public double getFlySpeed() {
		return 0;
	}
}
