package fly2.game.logic;

import fly2.phyzix.WorldItemCollection;

public class WeaponFactory {

	private WorldItemCollection worldItemCollection;

	public WeaponFactory(WorldItemCollection worldItemCollection) {
		this.worldItemCollection = worldItemCollection;
	}

	public Weapon makeGun() {
		Weapon gun = new Weapon(worldItemCollection);
		gun.setBulletDamage(1);
		gun.setBulletSize(0.5);
		gun.setBulletSpeed(1.0);

		return gun;
	}
}
