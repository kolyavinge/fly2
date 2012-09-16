package fly2.game.enemy;

import fly2.game.frontend.*;
import java.util.Collection;

/** Вражеский разум ! */
public abstract class EnemyBrain {

	private GameWorld gameWorld;
	private Plane player;
	private Plane enemy;
	private Collection<Plane> enemyPlanes;
	private Collection<Bullet> bullets;
	private StepResult stepResult;

	public abstract void activate();

	public final GameWorld getGameWorld() {
		return gameWorld;
	}

	public final Plane getPlayer() {
		return player;
	}

	public final Plane getEnemy() {
		return enemy;
	}

	public final Collection<Plane> getEnemyPlanes() {
		return enemyPlanes;
	}

	public final Collection<Bullet> getBullets() {
		return bullets;
	}

	public final StepResult getStepResult() {
		return stepResult;
	}

	public final void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public final void setPlayer(Plane player) {
		this.player = player;
	}

	public final void setEnemy(Plane enemy) {
		this.enemy = enemy;
	}

	public final void setEnemyPlanes(Collection<Plane> enemyPlanes) {
		this.enemyPlanes = enemyPlanes;
	}

	public final void setBullets(Collection<Bullet> bullets) {
		this.bullets = bullets;
	}

	public final void setStepResult(StepResult stepResult) {
		this.stepResult = stepResult;
	}
}
