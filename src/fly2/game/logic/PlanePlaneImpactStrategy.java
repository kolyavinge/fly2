package fly2.game.logic;

import fly2.phyzix.*;

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
