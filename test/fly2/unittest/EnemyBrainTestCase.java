package fly2.unittest;

import fly2.common.Direction;
import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.StepResult;
import fly2.game.frontend.Bullet;

import java.util.Collection;

import junit.framework.TestCase;

public class EnemyBrainTestCase extends TestCase {
	
	protected static final int FIRE = 1;
	protected static final int NO_FIRE = 2;
	
	private TestGameWorld gameWorld;
	protected TestPlane player;
	protected TestPlane enemy;
	private Collection<fly2.game.frontend.Plane> enemyPlanes;
	private Collection<fly2.game.frontend.Bullet> bullets;
	
	private EnemyBrain brain;
	
	protected void setBrain(EnemyBrain brain) {
		this.brain = brain;
	}

	protected void assertFireAndDirection(int fire, Direction moveDirection) {
		EnemyBrainContext context = getEnemyBrainContext();
		brain.setContext(context);
		StepResult stepResult = brain.activate();

		if (fire == FIRE) {
			assertTrue("fire", stepResult.isFire());
		} else if (fire == NO_FIRE) {
			assertFalse("fire", stepResult.isFire());
		} else {
			throw new RuntimeException();
		}

		assertEquals("direction", moveDirection, stepResult.getMoveDirection());
	}
	
	protected void createWorld(double width, double height) {
		gameWorld = new TestGameWorld(width, height);
		
		player = new TestPlane();
		player.setWidth(2.0);
		player.setHeight(1.5);

		enemy = new TestPlane();
		enemy.setWidth(2.0);
		enemy.setHeight(1.5);
	}

	protected void setEnemyPosition(double x, double y) {
		enemy.setX(x);
		enemy.setY(y);
	}
	
	protected void setPlayerPosition(double x, double y) {
		player.setX(x);
		player.setY(y);
	}
	
	protected EnemyBrainContext getEnemyBrainContext() {

		return new EnemyBrainContext() {

			public fly2.game.frontend.GameWorld getGameWorld() {
				return gameWorld;
			}

			public fly2.game.frontend.Plane getPlayer() {
				return player;
			}

			public fly2.game.frontend.Plane getEnemy() {
				return enemy;
			}

			public Collection<fly2.game.frontend.Plane> getEnemyPlanes() {
				return enemyPlanes;
			}

			public Collection<Bullet> getBullets() {
				return bullets;
			}
		};
	}
}
