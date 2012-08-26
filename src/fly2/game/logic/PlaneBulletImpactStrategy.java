package fly2.game.logic;

import fly2.phyzix.*;

/**
 * Сталкновение самолета и пульки
 */
public class PlaneBulletImpactStrategy implements ImpactStrategy<PlaneWorldItem, BulletWorldItem> {

	public Class<PlaneWorldItem> getFirstObjectClass() {
		return PlaneWorldItem.class;
	}

	public Class<BulletWorldItem> getSecondObjectClass() {
		return BulletWorldItem.class;
	}

	public void activateImpact(PlaneWorldItem plane, BulletWorldItem bullet) {
		plane.damage(bullet.getDamage());
		bullet.destroy();
	}
}
