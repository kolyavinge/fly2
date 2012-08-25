package fly2.model;

import fly2.core.*;

/**
 * Сталкновение самолета и пульки
 */
public class PlaneBulletImpactStrategy implements ImpactStrategy<DefaultPlane, Bullet> {

	public Class<DefaultPlane> getFirstObjectClass() {
		return DefaultPlane.class;
	}

	public Class<Bullet> getSecondObjectClass() {
		return Bullet.class;
	}

	public void activateImpact(DefaultPlane plane, Bullet bullet) {
		plane.damage(bullet.getDamage());
		bullet.destroy();
	}
}
