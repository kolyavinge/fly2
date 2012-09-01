package fly2.game.logic;

import java.util.*;

public class GameModel implements fly2.game.frontend.GameModel {

	private GameWorld gameWorld;

	public GameModel() {
		gameWorld = new GameWorld(25.0, 100.0);
	}

	public fly2.game.frontend.GameWorld getWorld() {
		return gameWorld;
	}

	public fly2.game.frontend.Plane getPlayerPlane() {
		return gameWorld.getPlayerPlane();
	}

	public Collection<fly2.game.frontend.Plane> getEnemyPlanes() {
		return Collections.<fly2.game.frontend.Plane> unmodifiableCollection(gameWorld.getEnemyPlanes());
	}

	public int getEnemyPlanesCount() {
		return gameWorld.getEnemyPlanesCount();
	}

	public Iterable<fly2.game.frontend.Bullet> getBullets() {
		return Collections.<fly2.game.frontend.Bullet> unmodifiableCollection(gameWorld.getBullets());
	}

	public int getBulletsCount() {
		return gameWorld.getBulletsCount();
	}
}