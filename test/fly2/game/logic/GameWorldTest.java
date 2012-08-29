package fly2.game.logic;

import junit.framework.TestCase;

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
		player.setSpeed(0.0);

		Weapon playerWeapon = player.getWeapon();
		playerWeapon.setPosition(0.5, 1.0);
		playerWeapon.setBulletSize(1.0);
		playerWeapon.setBulletSpeed(10.0);
		playerWeapon.setBulletDamage(1);

		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(0.0, 21.0);
		enemy.setSize(1.0, 1.0);
		enemy.setSpeed(0.0);

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
		player.setSpeed(10.0);

		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(0.0, 50.0);
		enemy.setSize(1.0, 10.0);
		enemy.setSpeed(10.0);

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
		player.setSpeed(0.0);

		Weapon playerWeapon = player.getWeapon();
		playerWeapon.setPosition(0.5, 1.0);
		playerWeapon.setBulletSpeed(worldHeight / 2.0);

		player.fire();

		gameWorld.update();
		assertEquals(1, gameWorld.getBulletsCount());

		gameWorld.update();
		assertEquals(0, gameWorld.getBulletsCount());
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
