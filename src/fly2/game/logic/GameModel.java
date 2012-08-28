package fly2.game.logic;

import fly2.game.frontend.GameModelFactory;

import java.util.*;

public class GameModel implements fly2.game.frontend.GameModel {

	static {
		System.setProperty(GameModelFactory.getGameModelClassPropName(), GameModel.class.getName());
	}

	private GameWorld gameWorld;

	public GameModel() {
		gameWorld = new GameWorld(25.0, 100.0);
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

	public fly2.game.frontend.GameWorld getWorld() {
		return gameWorld;
	}
}
