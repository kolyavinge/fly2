package fly2.model;

import fly2.core.*;

public class Plane extends MoveableWorldItem {

	private Weapon weapon;

	public Plane(Weapon weapon) {
		setWeapon(weapon);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		if (weapon == null)
			throw new NullPointerException("weapon");

		this.weapon = weapon;
	}

	public void fire() {
		weapon.fire();
	}
}
