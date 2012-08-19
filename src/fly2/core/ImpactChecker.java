package fly2.core;

import fly2.common.*;
import java.util.*;

public class ImpactChecker {

	public Collection<WorldItemTuple> checkImpact(List<WorldItem> worldItems) {
		Collection<WorldItemTuple> result = new ArrayList<WorldItemTuple>();

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
		Rectangle bounds1 = item1.getBounds();
		Rectangle bounds2 = item2.getBounds();

		return Geometry.impactRect(
				bounds1.getLeftUpX(), bounds1.getLeftUpY(), bounds1.getWidth(), bounds1.getHeight(),
				bounds2.getLeftUpX(), bounds2.getLeftUpY(), bounds2.getWidth(), bounds2.getHeight());
	}
}
