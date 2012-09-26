package fly2.game.logic;

import fly2.game.common.ResourceFileReader;

import java.util.Collection;
import java.util.Collections;

public final class GameModel implements fly2.game.frontend.GameModel {

	private GameWorld gameWorld;
//	private GameWorldFactory gameWorldFactory;
	private PlaneActions playerPlaneActions;
	// private EnemyBrainActivator brainActivator;

	public GameModel(ResourceFileReader fileReader) {
		GameWorldFactory gameWorldFactory = new GameWorldFactory(fileReader);
		gameWorld = gameWorldFactory.makeWorld(0);
		playerPlaneActions = new PlaneActions(gameWorld.getPlayerPlane());
		// brainActivator = new EnemyBrainActivator(gameWorld.getEnemyPlanes());
	}

	public void update() {
		gameWorld.update();
		// brainActivator.activate(gameWorld.getEnemyPlanes());
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

	public Collection<fly2.game.frontend.Bullet> getBullets() {
		return Collections.<fly2.game.frontend.Bullet> unmodifiableCollection(gameWorld.getBullets());
	}

	public int getBulletsCount() {
		return gameWorld.getBulletsCount();
	}

	public fly2.game.frontend.PlaneActions getPlayerPlaneActions() {
		return playerPlaneActions;
	}
}
