package fly2.game.logic;

import fly2.game.enemy.EnemyBrainContext;
import fly2.game.logic.PlaneActions;
import java.util.Collection;
import java.util.Collections;

final class EnemyBrainContextWrapper implements EnemyBrainContext {

	private fly2.game.logic.GameWorld gameWorld;
	private fly2.game.logic.Plane enemy;
	private fly2.game.frontend.PlaneActions actions;

	public EnemyBrainContextWrapper(
			fly2.game.logic.GameWorld gameWorld,
			fly2.game.logic.Plane enemy) {
		
		if (gameWorld == null)
			throw new NullPointerException("gameWorld");

		if (enemy == null)
			throw new NullPointerException("enemy");

		this.gameWorld = gameWorld;
		this.enemy = enemy;
		this.actions = new PlaneActions(enemy);
	}

	public fly2.game.frontend.GameWorld getGameWorld() {
		return gameWorld;
	}

	public fly2.game.frontend.Plane getPlayer() {
		return gameWorld.getPlayerPlane();
	}

	public fly2.game.frontend.Plane getEnemy() {
		return enemy;
	}

	public fly2.game.frontend.PlaneActions getEnemyActions() {
		return actions;
	}

	public Collection<fly2.game.frontend.Plane> getEnemyPlanes() {
		return Collections.<fly2.game.frontend.Plane>unmodifiableCollection(gameWorld.getEnemyPlanes());
	}

	public Collection<fly2.game.frontend.Bullet> getBullets() {
		return Collections.<fly2.game.frontend.Bullet>unmodifiableCollection(gameWorld.getBullets());
	}
}
