package fly2.model;

import fly2.core.*;

public class Plane extends MoveableWorldItem {

	private int health;
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health < 0)
			throw new IllegalArgumentException("health");

		this.health = health;
	}

	public void damage(int damageValue) {
		if (damageValue < 0)
			throw new IllegalArgumentException("damageValue");

		health -= damageValue;

		if (health < 0)
			health = 0;
	}

	public void fire() {
		weapon.fire();
	}
}
