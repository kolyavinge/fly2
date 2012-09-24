package fly2.game.enemy;

import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;
import fly2.unittest.TestGameWorld;
import fly2.unittest.TestPlane;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class EnemyBrainContextBuilderTest extends TestCase {

	private GameWorld gameWorld;
	private Plane player;
	private Plane enemy;
	private Collection<Plane> enemies;
	private Collection<Bullet> bullets;
	private EnemyBrainContextBuilder builder;

	public void setUp() {
		gameWorld = new TestGameWorld();
		player = new TestPlane();
		enemy = new TestPlane();
		enemies = new ArrayList<Plane>();
		bullets = new ArrayList<Bullet>();
		builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setPlayer(player);
		builder.setEnemy(enemy);
		builder.setEnemyPlanes(enemies);
		builder.setBullets(bullets);
	}

	public void testNew() {
		builder = new EnemyBrainContextBuilder();
		assertNull(builder.getGameWorld());
		assertNull(builder.getPlayer());
		assertNull(builder.getEnemy());
		assertNull(builder.getEnemyPlanes());
		assertNull(builder.getBullets());
	}

	public void testGetSet() {
		assertSame(gameWorld, builder.getGameWorld());
		assertSame(player, builder.getPlayer());
		assertSame(enemy, builder.getEnemy());
		assertSame(enemies, builder.getEnemyPlanes());
		assertSame(bullets, builder.getBullets());
	}

	public void testBuild() {
		EnemyBrainContext context = builder.build();
		assertSame(gameWorld, context.getGameWorld());
		assertSame(player, context.getPlayer());
		assertSame(enemy, context.getEnemy());
		assertSame(enemies, context.getEnemyPlanes());
		assertSame(bullets, context.getBullets());
	}

	public void testBuildSigleCall() {
		builder.build(); // first call
		
		try {
			builder.build();  // second call
			fail();
		} catch (IllegalStateException exp) {
		}
	}

	public void testInitError() {
		builder = new EnemyBrainContextBuilder();

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testGameWorldNotSet() {
		builder = new EnemyBrainContextBuilder();
		builder.setPlayer(player);
		builder.setEnemy(enemy);
		builder.setEnemyPlanes(enemies);
		builder.setBullets(bullets);

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testPlayerNotSet() {
		builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setEnemy(enemy);
		builder.setEnemyPlanes(enemies);
		builder.setBullets(bullets);

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testEnemyNotSet() {
		builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setPlayer(player);
		builder.setEnemyPlanes(enemies);
		builder.setBullets(bullets);

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testEnemyPlanesNotSet() {
		builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setPlayer(player);
		builder.setEnemy(enemy);
		builder.setBullets(bullets);

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testBulletsNotSet() {
		builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setPlayer(player);
		builder.setEnemy(enemy);
		builder.setEnemyPlanes(enemies);

		try {
			builder.build();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}
}
