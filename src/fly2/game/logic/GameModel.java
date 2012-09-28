package fly2.game.logic;

import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.EnemyBrainController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class GameModel implements fly2.game.frontend.GameModel {

	private GameWorld gameWorld;
	private EnemyBrainController brainController;
	private PlaneActions playerPlaneActions;

	public GameModel(GameWorldFactory gameWorldFactory, EnemyBrainController brainController) {
		this.gameWorld = gameWorldFactory.makeWorld(0);
		this.brainController = brainController;
		createEnemyPlanesContexts();
		this.playerPlaneActions = new PlaneActions(gameWorld.getPlayerPlane());
	}

	public void update() {
		gameWorld.update();
		brainController.activate();
	}

	private void createEnemyPlanesContexts() {
		Collection<EnemyBrainContext> contexts = new ArrayList<EnemyBrainContext>(gameWorld.getEnemyPlanes().size());
		for (Plane enemy : gameWorld.getEnemyPlanes()) {
			contexts.add(new EnemyBrainContextWrapper(gameWorld, enemy));
		}
		brainController.setContextCollection(contexts);
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

	public Collection<fly2.game.frontend.Bullet> getBullets() {
		return Collections.<fly2.game.frontend.Bullet> unmodifiableCollection(gameWorld.getBullets());
	}

	public fly2.game.frontend.PlaneActions getPlayerPlaneActions() {
		return playerPlaneActions;
	}
}
