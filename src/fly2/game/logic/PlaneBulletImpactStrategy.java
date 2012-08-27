package fly2.game.logic;

import fly2.phyzix.*;

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
		if (plane.getId() != bullet.getOwnerPlaneId()) {
			plane.damage(bullet.getDamage());
			bullet.destroy();
		}
	}
}
