package fly2.unittest;

import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameModel;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;
import fly2.game.frontend.PlayerPlaneActions;

public class TestGameModel implements GameModel {

	public Plane getPlayerPlane() {
		return null;
	}

	public void update() {
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

	public Iterable<Bullet> getBullets() {
		return null;
	}

	public int getBulletsCount() {
		return 0;
	}

	public PlayerPlaneActions getPlayerPlaneActions() {
		return null;
	}
	
}
