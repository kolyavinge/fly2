package fly2.model;

import fly2.core.*;

/**
 * Пуля
 */
public class Bullet extends MoveableWorldItem {

	private double damage;

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		if (damage < 0)
			throw new IllegalArgumentException();

		this.damage = damage;
	}
}
