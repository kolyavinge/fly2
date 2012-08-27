package fly2.game.logic;

import fly2.phyzix.*;

public final class GameWorld implements fly2.game.frontend.GameWorld {

	private World world;
	private Plane playerPlane;
	private PlaneFactory planeFactory;

	public GameWorld(double width, double height) {
		world = new World(width, height);
		world.registerImpactStrategy(new PlaneBulletImpactStrategy());
		world.registerImpactStrategy(new PlanePlaneImpactStrategy());
		planeFactory = new PlaneFactory(new WeaponFactory(world));
		playerPlane = planeFactory.makePlayer();
		world.addItem(playerPlane);
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

	public Plane getPlayerPlane() {
		return playerPlane;
	}

	public Plane createEnemyPlane() {
		Plane enemy = planeFactory.makeEnemy();
		world.addItem(enemy);

		return enemy;
	}

	public void update() {
		world.updateItems();
		world.activateOutOfWorldItems();
		world.activateItemsImpact();
		world.removeDestroyedItems();
	}
}
