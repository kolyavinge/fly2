package fly2.model;

import fly2.core.*;

/**
 * Сталкновение самолета и пульки
 */
public class PlaneBulletImpactStrategy implements ImpactStrategy<Plane, Bullet> {

	public Class<Plane> getFirstObjectClass() {
		return Plane.class;
	}

	public Class<Bullet> getSecondObjectClass() {
		return Bullet.class;
	}

	public void activateImpact(Plane plane, Bullet bullet) {
		plane.damage(bullet.getDamage());
		bullet.destroy();
	}
}
