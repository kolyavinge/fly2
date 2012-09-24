package fly2.game.enemy;

import fly2.game.frontend.*;

import java.util.Collection;

public final class EnemyBrainContextBuilder {

	private GameWorld gameWorld;
	private Plane player;
	private Plane enemy;
	private Collection<Plane> enemyPlanes;
	private Collection<Bullet> bullets;

	private boolean buildMethodHasCall = false;

	public EnemyBrainContext build() {
		// single call
		if (buildMethodHasCall) {
			throw new IllegalStateException("single call ! bitchhhh !");
		}

		verifyParams();
		buildMethodHasCall = true;

		return getEnemyBrainContextInstance();
	}

	private EnemyBrainContext getEnemyBrainContextInstance() {

		return new EnemyBrainContext() {

			public GameWorld getGameWorld() {
				return gameWorld;
			}

			public Plane getPlayer() {
				return player;
			}

			public Plane getEnemy() {
				return enemy;
			}

			public Collection<Plane> getEnemyPlanes() {
				return enemyPlanes;
			}

			public Collection<Bullet> getBullets() {
				return bullets;
			}
		};
	}

	private void verifyParams() {
		if (gameWorld == null)
			throw new EnemyBrainNotInitializeException("gameWorld");

		if (player == null)
			throw new EnemyBrainNotInitializeException("player");

		if (enemy == null)
			throw new EnemyBrainNotInitializeException("enemy");

		if (enemyPlanes == null)
			throw new EnemyBrainNotInitializeException("enemyPlanes");

		if (bullets == null)
			throw new EnemyBrainNotInitializeException("bullets");
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public Plane getPlayer() {
		return player;
	}

	public void setPlayer(Plane player) {
		this.player = player;
	}

	public Plane getEnemy() {
		return enemy;
	}

	public void setEnemy(Plane enemy) {
		this.enemy = enemy;
	}

	public Collection<Plane> getEnemyPlanes() {
		return enemyPlanes;
	}

	public void setEnemyPlanes(Collection<Plane> enemyPlanes) {
		this.enemyPlanes = enemyPlanes;
	}

	public Collection<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Collection<Bullet> bullets) {
		this.bullets = bullets;
	}
}
