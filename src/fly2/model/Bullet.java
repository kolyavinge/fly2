package fly2.model;

import fly2.core.*;

/**
 * Пуля
 */
public class Bullet extends MoveableWorldItem {

	private int damage;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if (damage < 0)
			throw new IllegalArgumentException();

		this.damage = damage;
	}
}
