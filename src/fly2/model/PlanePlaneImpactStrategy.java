package fly2.model;

import fly2.core.*;

/**
 * Соударение двух самолетов
 */
public class PlanePlaneImpactStrategy implements ImpactStrategy<DefaultPlane, DefaultPlane> {

	public Class<DefaultPlane> getFirstObjectClass() {
		return DefaultPlane.class;
	}

	public Class<DefaultPlane> getSecondObjectClass() {
		return DefaultPlane.class;
	}

	public void activateImpact(DefaultPlane plane1, DefaultPlane plane2) {
		plane1.destroy();
		plane2.destroy();
	}
}
