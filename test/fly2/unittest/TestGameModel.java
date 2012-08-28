package fly2.unittest;

import fly2.game.frontend.GameModel;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;

public class TestGameModel implements GameModel {

	public Plane getPlayerPlane() {
		return null;
	}

	public Iterable<Plane> getEnemyPlanes() {
		return null;
	}

	public int getEnemyPlanesCount() {
		return 0;
	}

	public GameWorld getWorld() {
		return null;
	}
}
