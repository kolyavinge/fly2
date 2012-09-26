package fly2.unittest.stub;

import fly2.game.logic.Plane;
import fly2.game.logic.PlaneFactory;

public class TestPlaneFactory implements PlaneFactory {

	public Plane makePlayer() {
		return new Plane();
	}

	public Plane makeEnemy() {
		return new Plane();
	}
}
