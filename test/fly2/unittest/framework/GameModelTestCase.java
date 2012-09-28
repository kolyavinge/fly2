package fly2.unittest.framework;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.EnemyBrainController;
import fly2.game.frontend.Direction;
import fly2.game.frontend.PlaneKind;
import fly2.game.logic.GameModel;
import fly2.game.logic.GameWorld;
import fly2.game.logic.GameWorldFactory;
import fly2.game.logic.Plane;
import fly2.unittest.stub.TestEnemyBrainContainer;
import fly2.unittest.stub.TestGameWorldFactory;
import junit.framework.TestCase;

public class GameModelTestCase extends TestCase {

	private Plane player;
	private Plane enemy;
	private GameWorld gameWorld;
	private GameModel gameModel;
	private double enemyPlaneStartX, enemyPlaneStartY;
	private TestGameWorldFactory gameWorldFactory;
	private EnemyBrainController enemyBrainController;

	protected void createWorld(double width, double height) {
		player = new Plane();
		player.setHealth(1);
		player.setFlySpeed(10);
		player.setFlyDirection(Direction.UP);

		enemy = new Plane();
		enemy.setHealth(1);
		enemy.setFlyDirection(Direction.DOWN);
		enemy.setKind(PlaneKind.ENEMY);

		gameWorld = new GameWorld(width, height);
		gameWorld.setPlayerPlane(player);
		gameWorld.addEnemyPlane(enemy);

		gameWorldFactory = new TestGameWorldFactory();
		gameWorldFactory.addWorld(0, gameWorld);

		TestEnemyBrainContainer enemyBrainContainer = new TestEnemyBrainContainer();
		enemyBrainContainer.addBrain(PlaneKind.ENEMY, enemyBrain);
		enemyBrainController = new EnemyBrainController();
		enemyBrainController.setBrainContainer(enemyBrainContainer);

		gameModel = new GameModel(gameWorldFactory, enemyBrainController);
	}
	
	protected GameWorldFactory getGameWorldFactory() {
		return gameWorldFactory;
	}
	
	protected EnemyBrainController getEnemyBrainController() {
		return enemyBrainController;
	}
	
	protected void createWorld() {
		createWorld(100, 100);
		setEnemyPosition(20, 20);
		setPlanesFlySpeed(10);
		setPlanesMoveSpeed(10);
	}

	protected void assertPlayerFly() {
		assertEquals(player.getFlySpeed(), player.getY(), 0.001);
	}

	protected void assertEnemyFly() {
		assertEquals(enemyPlaneStartY - enemy.getFlySpeed(), enemy.getY(), 0.001);
	}
	
	protected void assertEnemyBrainActivated() {
		assertEquals(enemyPlaneStartX + enemy.getMoveSpeed(), enemy.getX(), 0.001);
	}

	protected void updateGameModel() {
		gameModel.update();
	}

	protected void setEnemyPosition(double x, double y) {
		enemyPlaneStartX = x;
		enemyPlaneStartY = y;
		enemy.setPosition(x, y);
	}

	protected void setPlanesFlySpeed(double speed) {
		player.setFlySpeed(speed);
		enemy.setFlySpeed(speed);
	}

	protected void setPlanesMoveSpeed(double speed) {
		player.setMoveSpeed(speed);
		enemy.setMoveSpeed(speed);
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
