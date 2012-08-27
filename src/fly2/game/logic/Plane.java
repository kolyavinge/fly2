package fly2.game.logic;

import fly2.phyzix.ext.MoveableWorldItem;

public class Plane extends MoveableWorldItem implements fly2.game.frontend.Plane {

	private static int lastId = 1;
	
	private int health;
	private Weapon weapon;
	private int id;

	public Plane(Weapon weapon) {
		id = lastId++;
		setWeapon(weapon);
	}

	public Plane() {
		id = lastId++;
		// weapon = new NullWeapon();
	}

	public int getId() {
		return id;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		if (weapon == null)
			throw new NullPointerException("weapon");

		this.weapon = weapon;
		this.weapon.setOwnerPlaneId(id);
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
		if (weapon == null)
			throw new IllegalStateException("Plane weapon was null");

		weapon.fire();
	}

	@Override
	public void setX(double x) {
		double oldX = getX();
		super.setX(x);
		if (weapon != null) {
			double newX = getX();
			double deltaX = newX - oldX;
			weapon.moveX(deltaX);
		}
	}

	@Override
	public void setY(double y) {
		double oldY = getY();
		super.setY(y);
		if (weapon != null) {
			double newY = getY();
			double deltaY = newY - oldY;
			weapon.moveY(deltaY);
		}
	}

	@Override
	public void moveX(double value) {
		super.moveX(value);
		if (weapon != null)
			weapon.moveX(value);
	}

	@Override
	public void moveY(double value) {
		super.moveY(value);
		if (weapon != null)
			weapon.moveY(value);
	}
}
