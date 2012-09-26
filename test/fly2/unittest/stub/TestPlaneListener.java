package fly2.unittest.stub;

import fly2.game.logic.Bullet;
import fly2.game.logic.Plane;
import fly2.game.logic.PlaneListener;

public class TestPlaneListener implements PlaneListener {

	public boolean onMoveLeftCall = false;
	public boolean onMoveRightCall = false;
	public boolean onFireCall = false;
	public Plane plane;
	public Bullet bullet;

	public void onMoveLeft(Plane plane) {
		this.onMoveLeftCall = true;
		this.plane = plane;
	}

	public void onMoveRight(Plane plane) {
		this.onMoveRightCall = true;
		this.plane = plane;
	}

	public void onFire(Plane plane, Bullet bullet) {
		this.onFireCall = true;
		this.plane = plane;
		this.bullet = bullet;
	}
}
