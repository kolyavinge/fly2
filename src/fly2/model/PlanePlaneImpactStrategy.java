package fly2.model;

import fly2.core.*;

/**
 * Соударение двух самолетов
 */
public class PlanePlaneImpactStrategy implements ImpactStrategy<Plane, Plane> {

	public Class<Plane> getFirstObjectClass() {
		return Plane.class;
	}

	public Class<Plane> getSecondObjectClass() {
		return Plane.class;
	}

	public void activateImpact(Plane plane1, Plane plane2) {
		plane1.destroy();
		plane2.destroy();
	}
}
