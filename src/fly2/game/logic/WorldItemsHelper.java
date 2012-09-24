package fly2.game.logic;

import fly2.phyzix.WorldItem;

import java.util.ArrayList;
import java.util.Collection;

public final class WorldItemsHelper {

	private WorldItemsHelper() {
	}

	public static Collection<Plane> getEnemyPlanes(Iterable<WorldItem> worldItems, WorldItem playerPlane) {
		Collection<Plane> enemyPlanes = new ArrayList<Plane>();
		for (WorldItem item : worldItems) {
			if ((item instanceof Plane) && (item != playerPlane))
				enemyPlanes.add((Plane) item);
		}

		return enemyPlanes;
	}

	public static int getEnemyPlanesCount(Iterable<WorldItem> worldItems, WorldItem playerPlane) {
		int count = 0;
		for (WorldItem item : worldItems) {
			if ((item instanceof Plane) && (item != playerPlane))
				count++;
		}

		return count;
	}

	public static Collection<Bullet> getBullets(Iterable<WorldItem> worldItems) {
		Collection<Bullet> bullets = new ArrayList<Bullet>();
		for (WorldItem item : worldItems) {
			if (item instanceof Bullet)
				bullets.add((Bullet) item);
		}

		return bullets;
	}

	public static int getBulletsCount(Iterable<WorldItem> worldItems) {
		int count = 0;
		for (WorldItem item : worldItems) {
			if (item instanceof Bullet)
				count++;
		}

		return count;
	}
}
