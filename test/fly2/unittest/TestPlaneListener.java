package fly2.unittest;

import fly2.game.logic.Bullet;
import fly2.game.logic.Plane;
import fly2.game.logic.PlaneListener;

public class TestPlaneListener implements PlaneListener {

	public boolean onFireHasCall = false;
	public Plane plane;
	public Bullet bullet;

	public void onFire(Plane plane, Bullet bullet) {
		this.onFireHasCall = true;
		this.plane = plane;
		this.bullet = bullet;
	}
}
