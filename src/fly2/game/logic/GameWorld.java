package fly2.game.logic;

import fly2.phyzix.World;
import fly2.phyzix.WorldItem;
import fly2.phyzix.ext.ReturnedOutOfWorldStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class GameWorld implements fly2.game.frontend.GameWorld {

	private World world;
	private Plane playerPlane;
	private PlaneFactory planeFactory;

	public GameWorld(double width, double height) {
		initWorld(width, height);
		initPlaneFactory();
		initPlayerPlane();
	}

	private void initPlayerPlane() {
		playerPlane = planeFactory.makePlayer();
		playerPlane.setPosition(
				(world.getWidth() - playerPlane.getWidth()) / 2.0,
				0.0);
		world.addItem(playerPlane);
		world.registerOutOfWorldStrategy(playerPlane, new ReturnedOutOfWorldStrategy<WorldItem>());
	}

	private void initPlaneFactory() {
		planeFactory = new PlaneFactory(new WeaponFactory(world));
	}

	private void initWorld(double width, double height) {
		world = new World(width, height);
		world.registerImpactStrategy(new PlaneBulletImpactStrategy());
		world.registerImpactStrategy(new PlanePlaneImpactStrategy());
	}

	public double getWidth() {
		return world.getWidth();
	}

	public double getHeight() {
		return world.getHeight();
	}

	public int getBulletsCount() {
		int count = 0;
		for (WorldItem item : world.getItems()) {
			if (item instanceof Bullet)
				count++;
		}

		return count;
	}

	public Collection<Bullet> getBullets() {
		List<Bullet> bullets = new ArrayList<Bullet>();
		for (WorldItem item : world.getItems()) {
			if ((item instanceof Bullet))
				bullets.add((Bullet) item);
		}

		return bullets;
	}

	public Plane getPlayerPlane() {
		return playerPlane;
	}

	public Plane createEnemyPlane() {
		Plane enemy = planeFactory.makeEnemy();
		world.addItem(enemy);

		return enemy;
	}

	public Collection<Plane> getEnemyPlanes() {
		List<Plane> enemyPlanes = new ArrayList<Plane>();
		for (WorldItem item : world.getItems()) {
			if ((item instanceof Plane) && (item != playerPlane))
				enemyPlanes.add((Plane) item);
		}

		return enemyPlanes;
	}

	public int getEnemyPlanesCount() {
		int count = 0;
		for (WorldItem item : world.getItems()) {
			if ((item instanceof Plane) && (item != playerPlane))
				count++;
		}

		return count;
	}

	public void update() {
		world.updateItems();
		world.activateOutOfWorldItems();
		world.activateItemsImpact();
		world.removeDestroyedItems();
	}
}
