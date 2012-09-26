package fly2.game.logic;

import static fly2.game.frontend.Direction.DOWN;
import static fly2.game.frontend.Direction.UP;
import fly2.unittest.framework.GameWorldTestCase;

public class GameWorldTest extends GameWorldTestCase {

	private final double worldWidth = 100.0;
	private final double worldHeight = 200.0;

	public void setUp() {
		createWorld(worldWidth, worldHeight);
	}

	public void testPlayerBulletDamageEnemyPlane() {
		Plane player = createPlayer();
		player.setPosition(0.0, 0.0);
		player.setSize(1.0, 1.0);
		player.setFlySpeed(0.0);
		player.setHealth(100);

		Weapon playerWeapon = player.getWeapon();
		playerWeapon.setPosition(0.5, 1.0);
		playerWeapon.setBulletSize(1.0);
		playerWeapon.setBulletSpeed(10.0);
		playerWeapon.setBulletDamage(1);

		Plane enemy = createEnemy();
		enemy.setPosition(0.0, 21.0);
		enemy.setSize(1.0, 1.0);
		enemy.setFlySpeed(0.0);
		enemy.setHealth(100);

		int playerInitHealth = player.getHealth();
		int enemyInitHealth = enemy.getHealth();

		player.fire();

		gameWorld.update();
		assertEquals(1, gameWorld.getBulletsCount());
		assertEquals(playerInitHealth, player.getHealth());
		assertEquals(enemyInitHealth, enemy.getHealth());

		gameWorld.update();
		assertEquals(0, gameWorld.getBulletsCount());
		assertEquals(playerInitHealth, player.getHealth());
		assertEquals(enemyInitHealth - playerWeapon.getBulletDamage(), enemy.getHealth());
	}

	public void testPlanesImpact() {
		Plane player = createPlayer();
		player.setPosition(0.0, 0.0);
		player.setSize(1.0, 10.0);
		player.setFlySpeed(10.0);
		player.setHealth(1);

		Plane enemy = createEnemy();
		enemy.setPosition(0.0, 50.0);
		enemy.setSize(1.0, 10.0);
		enemy.setFlySpeed(10.0);
		enemy.setHealth(1);

		gameWorld.update();
		assertFalse(player.isDestroyed());
		assertFalse(enemy.isDestroyed());

		gameWorld.update();
		assertTrue(player.isDestroyed());
		assertTrue(enemy.isDestroyed());
	}

	public void testBulletOutOfWorld() {
		Plane player = createPlayer();
		player.setPosition(0.0, 0.0);
		player.setFlySpeed(0.0);

		Weapon playerWeapon = player.getWeapon();
		playerWeapon.setPosition(0.5, 1.0);
		playerWeapon.setBulletSpeed(worldHeight / 2.0);

		player.fire();

		gameWorld.update();
		assertEquals(1, gameWorld.getBulletsCount());

		gameWorld.update();
		assertEquals(0, gameWorld.getBulletsCount());
	}

	public void testPlayerPlaneOutOfWorldLeft() {
		Plane player = createPlayer();
		player.setPosition(-1.0, 1.0);
		player.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(0.0, player.getX(), 0.001);
		assertEquals(1.0, player.getY(), 0.001);
	}

	public void testPlayerPlaneOutOfWorldRight() {
		Plane player = createPlayer();
		player.setPosition(gameWorld.getWidth() + 10.0, 1.0);
		player.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(gameWorld.getWidth() - player.getWidth(), player.getX(), 0.001);
		assertEquals(1.0, player.getY(), 0.001);
	}

	public void testPlayerPlaneOutOfWorldLeft2() {
		Plane player = createPlayer();
		player.setPosition(2.0, 10.0);
		player.setMoveSpeed(10.0);
		player.moveLeft();
		assertEquals(0.0, player.getX(), 0.001);
		assertEquals(10.0, player.getY(), 0.001);
	}

	public void testPlayerPlaneOutOfWorldRight2() {
		Plane player = createPlayer();
		player.setPosition(2.0, 10.0);
		player.setMoveSpeed(worldWidth);
		player.moveRight();
		assertEquals(worldWidth - player.getWidth(), player.getX(), 0.001);
		assertEquals(10.0, player.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldLeft() {
		Plane enemy = createEnemy();
		enemy.setPosition(-1.0, 1.0);
		enemy.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(0.0, enemy.getX(), 0.001);
		assertEquals(1.0, enemy.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldRight() {
		Plane enemy = createEnemy();
		enemy.setPosition(worldWidth + 10.0, 1.0);
		enemy.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(worldWidth - enemy.getWidth(), enemy.getX(), 0.001);
		assertEquals(1.0, enemy.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldLeft2() {
		Plane enemy = createEnemy();
		enemy.setPosition(2.0, 10.0);
		enemy.setMoveSpeed(10.0);
		enemy.moveLeft();
		assertEquals(0.0, enemy.getX(), 0.001);
		assertEquals(10.0, enemy.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldRight2() {
		Plane enemy = createEnemy();
		enemy.setPosition(2.0, 10.0);
		enemy.setMoveSpeed(worldWidth);
		enemy.moveRight();
		assertEquals(worldWidth - enemy.getWidth(), enemy.getX(), 0.001);
		assertEquals(10.0, enemy.getY(), 0.001);
	}

	public void testPlayerPlaneFly() {
		Plane player = createPlayer();
		player.setFlySpeed(1.0);
		assertTrue(player.getFlySpeed() > 0.0);
		assertEquals(UP, player.getFlyDirection());
		double oldY = player.getY();
		gameWorld.update();
		double newY = player.getY();
		assertEquals(newY - oldY, player.getFlySpeed(), 0.001);
	}

	public void testEnemyPlaneFly() {
		Plane enemy = createEnemy();
		enemy.setPosition(0.0, 10.0);
		enemy.setFlySpeed(1.0);
		assertTrue(enemy.getFlySpeed() > 0.0);
		assertEquals(DOWN, enemy.getFlyDirection());
		double oldY = enemy.getY();
		gameWorld.update();
		double newY = enemy.getY();
		// бот летит вниз => разность (newY - oldY) будет отрицательной
		assertEquals(newY - oldY, -enemy.getFlySpeed(), 0.001);
	}

	public void testSize() {
		assertEquals(worldWidth, gameWorld.getWidth());
		assertEquals(worldHeight, gameWorld.getHeight());
	}

	public void testGetPlayerPlane() {
		Plane player = createPlayer();
		assertSame(gameWorld, player.getListener());
	}

	public void testStartPlayerPosition() {
		Plane player = createPlayer();
		assertEquals(worldWidth / 2.0, player.getMiddleX(), 0.001);
		assertEquals(0.0, player.getY(), 0.001);
	}
}
