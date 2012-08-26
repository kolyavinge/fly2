package fly2.game.logic;

import fly2.game.frontend.Plane;
import fly2.game.frontend.Weapon;
import fly2.phyzix.*;
import fly2.phyzix.ext.MoveableWorldItem;

public class PlaneWorldItem extends MoveableWorldItem implements Plane {

	private int health;
	private Weapon weapon;

	public PlaneWorldItem(Weapon weapon) {
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

	public boolean isDestroyed() {
		return health == 0;
	}

	public void destroy() {
		health = 0;
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

	@Override
	public void moveX(double value) {
		super.moveX(value);
		weapon.moveX(value);
	}

	@Override
	public void moveY(double value) {
		super.moveY(value);
		weapon.moveY(value);
	}
}