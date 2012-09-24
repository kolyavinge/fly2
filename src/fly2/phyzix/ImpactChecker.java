package fly2.phyzix;

import fly2.common.*;
import java.util.*;

/**
 * Занимается проверкой соударения игровых объектов
 */
final class ImpactChecker {

	/**
	 * Ищит объекты которые ударились и возвращает их в виде коллекции кортежей.
	 */
	public Collection<WorldItemTuple> checkImpact(ArrayList<WorldItem> worldItems) {
		Collection<WorldItemTuple> result = new ArrayList<WorldItemTuple>(worldItems.size());

		for (int i = 0; i < worldItems.size() - 1; i++) {
			for (int j = i + 1; j < worldItems.size(); j++) {
				WorldItem item1 = worldItems.get(i);
				WorldItem item2 = worldItems.get(j);
				if (isImpacted(item1, item2)) {
					WorldItemTuple tuple = new WorldItemTuple(item1, item2);
					result.add(tuple);
				}
			}
		}

		return result;
	}

	private boolean isImpacted(WorldItem item1, WorldItem item2) {
		return Geometry.impactRect(
				item1.getX(), item1.getY(), item1.getWidth(), item1.getHeight(),
				item2.getX(), item2.getY(), item2.getWidth(), item2.getHeight());
	}
}
