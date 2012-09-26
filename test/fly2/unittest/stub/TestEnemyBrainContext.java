package fly2.unittest.stub;

import java.util.Collection;

import fly2.game.enemy.EnemyBrainContext;
import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;
import fly2.game.frontend.PlaneActions;

public class TestEnemyBrainContext implements EnemyBrainContext {

	private PlaneActions actions;
	private Plane enemy;

	public GameWorld getGameWorld() {
		return null;
	}

	public Plane getPlayer() {
		return null;
	}

	public Plane getEnemy() {
		return enemy;
	}

	public void setEnemy(Plane enemy) {
		this.enemy = enemy;
	}

	public PlaneActions getEnemyActions() {
		return actions;
	}

	public void setEnemyActions(PlaneActions actions) {
		this.actions = actions;
	}

	public Collection<Plane> getEnemyPlanes() {
		return null;
	}

	public Collection<Bullet> getBullets() {
		return null;
	}
}
