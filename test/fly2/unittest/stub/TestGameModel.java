package fly2.unittest.stub;

import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameModel;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;
import fly2.game.frontend.PlaneActions;

import java.util.Collection;

public class TestGameModel implements GameModel {

	public Plane getPlayerPlane() {
		return null;
	}

	public void update() {
	}

	public Collection<Plane> getEnemyPlanes() {
		return null;
	}

	public GameWorld getWorld() {
		return null;
	}

	public Collection<Bullet> getBullets() {
		return null;
	}

	public PlaneActions getPlayerPlaneActions() {
		return null;
	}
}
