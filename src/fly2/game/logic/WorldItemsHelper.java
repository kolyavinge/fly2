package fly2.game.logic;

import static fly2.game.frontend.PlaneKind.*;
import fly2.phyzix.WorldItem;
import java.util.ArrayList;
import java.util.Collection;

final class WorldItemsHelper {

	private WorldItemsHelper() {
	}

	public static Collection<Plane> getEnemyPlanes(Iterable<WorldItem> worldItems) {
		Collection<Plane> enemyPlanes = new ArrayList<Plane>();
		for (WorldItem item : worldItems) {
			if ((item instanceof Plane) && ((Plane)item).getKind() == ENEMY)
				enemyPlanes.add((Plane) item);
		}

		return enemyPlanes;
	}

	public static int getEnemyPlanesCount(Iterable<WorldItem> worldItems) {
		int count = 0;
		for (WorldItem item : worldItems) {
			if ((item instanceof Plane) && ((Plane)item).getKind() == ENEMY)
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
