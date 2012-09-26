package fly2.unittest.framework;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.frontend.Bullet;
import fly2.game.frontend.Direction;
import fly2.unittest.stub.TestGameWorld;
import fly2.unittest.stub.TestPlane;
import fly2.unittest.stub.TestPlaneActions;

import java.util.Collection;

import junit.framework.TestCase;

public class EnemyBrainTestCase extends TestCase {

	protected static final int FIRE = 1;
	protected static final int NO_FIRE = 2;

	private TestGameWorld gameWorld;
	protected TestPlane player;
	protected TestPlane enemy;
	private TestPlaneActions actions;
	private Collection<fly2.game.frontend.Plane> enemyPlanes;
	private Collection<fly2.game.frontend.Bullet> bullets;
	private EnemyBrain brain;

	protected void setBrain(EnemyBrain brain) {
		this.brain = brain;
	}

	protected void assertFireAndDirection(int fire, Direction moveDirection) {
		actions = new TestPlaneActions();
		EnemyBrainContext context = getEnemyBrainContext();
		brain.setContext(context);
		brain.activate();
		assertFire(fire);
		assertMoveDirection(moveDirection);
	}

	private void assertFire(int fire) {
		if (fire == FIRE) {
			assertTrue("fire", actions.fireFlag);
		} else if (fire == NO_FIRE) {
			assertFalse("fire", actions.fireFlag);
		} else {
			throw new IllegalStateException("fire");
		}
	}

	private void assertMoveDirection(Direction moveDirection) {
		if (moveDirection == Direction._UNDEFINED) {
			assertTrue("direction", actions.noMoveFlag);
		} else if (moveDirection == Direction.LEFT) {
			assertTrue("direction", actions.moveLeftFlag);
		} else if (moveDirection == Direction.RIGHT) {
			assertTrue("direction", actions.moveRightFlag);
		} else {
			throw new IllegalStateException("direction");
		}
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

			public fly2.game.frontend.PlaneActions getEnemyActions() {
				return actions;
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
