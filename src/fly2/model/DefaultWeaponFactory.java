package fly2.model;

import fly2.core.*;

public class DefaultWeaponFactory implements WeaponFactory {

	private WorldItemCollection worldItemCollection;

	public DefaultWeaponFactory(WorldItemCollection worldItemCollection) {
		this.worldItemCollection = worldItemCollection;
	}

	public Weapon makeGun() {
		DefaultWeapon gun = new DefaultWeapon(worldItemCollection);
		gun.setBulletDamage(2);
		gun.setBulletSize(6.0);
		gun.setBulletSpeed(10.0);

		return gun;
	}
}
