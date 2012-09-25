package fly2.game.logic;

import fly2.game.frontend.PlaneKind;
import fly2.phyzix.WorldItem;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class WorldItemsHelperTest extends TestCase {

	private ArrayList<WorldItem> items;
	private Plane player;
	private Plane enemy1, enemy2;
	private Bullet bullet1, bullet2;

	public void setUp() {
		items = new ArrayList<WorldItem>();
		items.add(player = new Plane());
		items.add(enemy1 = new Plane());
		items.add(enemy2 = new Plane());
		items.add(bullet1 = new Bullet());
		items.add(bullet2 = new Bullet());
		player.setKind(PlaneKind.PLAYER);
		enemy1.setKind(PlaneKind.ENEMY);
		enemy2.setKind(PlaneKind.ENEMY);
	}

	public void testGetEnemyPlanes() {
		Collection<Plane> enemyPlanes = WorldItemsHelper.getEnemyPlanes(items);
		assertEquals(2, enemyPlanes.size());
		assertTrue(enemyPlanes.contains(enemy1));
		assertTrue(enemyPlanes.contains(enemy2));
	}

	public void testGetEnemyPlanesCount() {
		int enemyPlanesCount = WorldItemsHelper.getEnemyPlanesCount(items);
		assertEquals(2, enemyPlanesCount);
	}

	public void testGetBullets() {
		Collection<Bullet> bullets = WorldItemsHelper.getBullets(items);
		assertEquals(2, bullets.size());
		assertTrue(bullets.contains(bullet1));
		assertTrue(bullets.contains(bullet2));
	}

	public void testGetBulletsCount() {
		int bulletsCount = WorldItemsHelper.getBulletsCount(items);
		assertEquals(2, bulletsCount);
	}
}
