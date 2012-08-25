package fly2.gamemodel.phyzix;

import fly2.phyzix.*;

/**
 * Соударение двух самолетов
 */
public class PlanePlaneImpactStrategy implements ImpactStrategy<PlaneWorldItem, PlaneWorldItem> {

	public Class<PlaneWorldItem> getFirstObjectClass() {
		return PlaneWorldItem.class;
	}

	public Class<PlaneWorldItem> getSecondObjectClass() {
		return PlaneWorldItem.class;
	}

	public void activateImpact(PlaneWorldItem plane1, PlaneWorldItem plane2) {
		plane1.destroy();
		plane2.destroy();
	}
}
