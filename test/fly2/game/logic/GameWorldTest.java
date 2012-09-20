package fly2.game.logic;

import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class GameWorldTest extends TestCase {

	private GameWorld gameWorld;
	private final double worldWidth = 100.0;
	private final double worldHeight = 200.0;

	public void setUp() {
		gameWorld = new GameWorld(worldWidth, worldHeight);
	}

	public void testSize() {
		assertEquals(worldWidth, gameWorld.getWidth());
		assertEquals(worldHeight, gameWorld.getHeight());
	}

	public void testPlayerBulletDamageEnemyPlane() {
		Plane player = gameWorld.getPlayerPlane();
		player.setPosition(0.0, 0.0);
		player.setSize(1.0, 1.0);
		player.setFlySpeed(0.0);

		Weapon playerWeapon = player.getWeapon();
		playerWeapon.setPosition(0.5, 1.0);
		playerWeapon.setBulletSize(1.0);
		playerWeapon.setBulletSpeed(10.0);
		playerWeapon.setBulletDamage(1);

		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(0.0, 21.0);
		enemy.setSize(1.0, 1.0);
		enemy.setFlySpeed(0.0);

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
		assertEquals(enemyInitHealth - 1, enemy.getHealth());
	}

	public void testPlanesImpact() {
		Plane player = gameWorld.getPlayerPlane();
		player.setPosition(0.0, 0.0);
		player.setSize(1.0, 10.0);
		player.setFlySpeed(10.0);

		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(0.0, 50.0);
		enemy.setSize(1.0, 10.0);
		enemy.setFlySpeed(10.0);

		gameWorld.update();
		assertFalse(player.isDestroyed());
		assertFalse(enemy.isDestroyed());

		gameWorld.update();
		assertTrue(player.isDestroyed());
		assertTrue(enemy.isDestroyed());
	}

	public void testBulletOutOfWorld() {
		Plane player = gameWorld.getPlayerPlane();
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
		Plane player = gameWorld.getPlayerPlane();
		player.setPosition(-1.0, 1.0);
		player.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(0.0, player.getX(), 0.001);
		assertEquals(1.0, player.getY(), 0.001);
	}

	public void testPlayerPlaneOutOfWorldRight() {
		Plane player = gameWorld.getPlayerPlane();
		player.setPosition(gameWorld.getWidth() + 10.0, 1.0);
		player.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(gameWorld.getWidth() - player.getWidth(), player.getX(), 0.001);
		assertEquals(1.0, player.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldLeft() {
		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(-1.0, 1.0);
		enemy.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(0.0, enemy.getX(), 0.001);
		assertEquals(1.0, enemy.getY(), 0.001);
	}

	public void testEnemyPlaneOutOfWorldRight() {
		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(gameWorld.getWidth() + 10.0, 1.0);
		enemy.setFlySpeed(0.0);
		gameWorld.update();
		assertEquals(gameWorld.getWidth() - enemy.getWidth(), enemy.getX(), 0.001);
		assertEquals(1.0, enemy.getY(), 0.001);
	}

	public void testPlayerPlaneFly() {
		Plane player = gameWorld.getPlayerPlane();
		assertTrue(player.getFlySpeed() > 0.0);
		assertEquals(UP, player.getFlyDirection());
		double oldY = player.getY();
		gameWorld.update();
		double newY = player.getY();
		assertEquals(newY - oldY, player.getFlySpeed(), 0.001);
	}

	public void testEnemyPlaneFly() {
		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(0.0, 10.0);
		assertTrue(enemy.getFlySpeed() > 0.0);
		assertEquals(DOWN, enemy.getFlyDirection());
		double oldY = enemy.getY();
		gameWorld.update();
		double newY = enemy.getY();
		// бот летит вниз => разность (newY - oldY) будет отрицательной
		assertEquals(newY - oldY, -enemy.getFlySpeed(), 0.001);
	}
	
	public void testGetPlayerPlane() {
		Plane player = gameWorld.getPlayerPlane();
		assertSame(gameWorld, player.getListener());
	}
	
	public void testCreateEnemyPlane() {
		Plane enemy = gameWorld.createEnemyPlane();
		assertSame(gameWorld, enemy.getListener());
	}

	public void testStartPlayerPosition() {
		Plane player = gameWorld.getPlayerPlane();
		assertEquals(worldWidth / 2.0, player.getMiddleX(), 0.001);
		assertEquals(0.0, player.getY(), 0.001);
	}

	public void testGetEnemyPlanes() {
		assertEquals(0, gameWorld.getEnemyPlanes().size());
		assertEquals(0, gameWorld.getEnemyPlanesCount());
		gameWorld.createEnemyPlane();
		assertEquals(1, gameWorld.getEnemyPlanes().size());
		assertEquals(1, gameWorld.getEnemyPlanesCount());
	}

	public void testGetBullets() {
		assertEquals(0, gameWorld.getBullets().size());
		gameWorld.getPlayerPlane().fire();
		assertEquals(1, gameWorld.getBullets().size());
	}
}
