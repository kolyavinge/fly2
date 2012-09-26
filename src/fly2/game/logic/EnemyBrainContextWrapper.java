package fly2.game.logic;

import fly2.game.enemy.EnemyBrainContext;
import fly2.game.frontend.PlaneActions;

import java.util.Collection;

public class EnemyBrainContextWrapper implements EnemyBrainContext {

	private fly2.game.frontend.GameModel gameModel;
	private fly2.game.logic.Plane enemy;
	private fly2.game.frontend.PlaneActions actions;

	public EnemyBrainContextWrapper(
			fly2.game.frontend.GameModel gameModel,
			fly2.game.logic.Plane enemy) {
		
		if (gameModel == null)
			throw new NullPointerException("gameModel");

		if (enemy == null)
			throw new NullPointerException("enemy");

		this.gameModel = gameModel;
		this.enemy = enemy;
		this.actions = new fly2.game.logic.PlaneActions(enemy);
	}

	public fly2.game.frontend.GameWorld getGameWorld() {
		return gameModel.getWorld();
	}

	public fly2.game.frontend.Plane getPlayer() {
		return gameModel.getPlayerPlane();
	}

	public fly2.game.frontend.Plane getEnemy() {
		return enemy;
	}

	public PlaneActions getEnemyActions() {
		return actions;
	}

	public Collection<fly2.game.frontend.Plane> getEnemyPlanes() {
		return gameModel.getEnemyPlanes();
	}

	public Collection<fly2.game.frontend.Bullet> getBullets() {
		return gameModel.getBullets();
	}
}
