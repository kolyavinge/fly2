package fly2.game.logic;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.EnemyBrainController;
import fly2.game.frontend.Direction;
import fly2.game.frontend.PlaneKind;
import fly2.unittest.stub.TestEnemyBrainContainer;
import fly2.unittest.stub.TestGameWorldFactory;
import junit.framework.TestCase;

public class GameModelTest extends TestCase {

	private Plane player;
	private Plane enemy;
	private final double enemyPlaneStartX = 10.0;
	private final double enemyPlaneStartY = 50.0;
	private GameWorld gameWorld;
	private GameModel gameModel;

	public void setUp() {
		player = getPlayerPlane();
		enemy = getEnemyPlane();
		gameWorld = getWorld(player, enemy);

		TestGameWorldFactory gameWorldFactory = new TestGameWorldFactory();
		gameWorldFactory.addWorld(0, gameWorld);

		TestEnemyBrainContainer enemyBrainContainer = new TestEnemyBrainContainer();
		enemyBrainContainer.addBrain(PlaneKind.ENEMY, enemyBrain);
		EnemyBrainController enemyBrainController = new EnemyBrainController(enemyBrainContainer);

		gameModel = new GameModel(gameWorldFactory, enemyBrainController);
	}

	public void testUpdate() {
		gameModel.update();
		assertEquals(player.getFlySpeed(), player.getY(), 0.001);
		assertEquals(enemyPlaneStartY - enemy.getFlySpeed(), enemy.getY(), 0.001);
		assertEquals(enemyPlaneStartX + enemy.getMoveSpeed(), enemy.getX(), 0.001);
	}

	private GameWorld getWorld(Plane player, Plane enemy) {
		GameWorld gameWorld = new GameWorld(100, 200);
		gameWorld.setPlayerPlane(player);
		gameWorld.addEnemyPlane(enemy);

		return gameWorld;
	}

	private Plane getEnemyPlane() {
		Plane enemy = new Plane();
		enemy.setPosition(enemyPlaneStartX, enemyPlaneStartY);
		enemy.setHealth(1);
		enemy.setFlySpeed(10);
		enemy.setFlyDirection(Direction.DOWN);
		enemy.setMoveSpeed(20);
		enemy.setKind(PlaneKind.ENEMY);

		return enemy;
	}

	private Plane getPlayerPlane() {
		Plane player = new Plane();
		player.setHealth(1);
		player.setFlySpeed(10);
		player.setFlyDirection(Direction.UP);

		return player;
	}

	private final EnemyBrain enemyBrain = new EnemyBrain() {

		private EnemyBrainContext context;

		public void setContext(EnemyBrainContext context) {
			this.context = context;
		}

		public void activate() {
			this.context.getEnemyActions().moveRight();
		}
	};
}
