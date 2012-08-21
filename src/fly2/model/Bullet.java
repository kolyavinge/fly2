package fly2.model;

import fly2.core.*;

/**
 * Пуля
 */
public class Bullet extends MoveableWorldItem implements Destroyable {

	private int damage;
	private boolean isDestroyed;

	public Bullet() {
		isDestroyed = false;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if (damage < 0)
			throw new IllegalArgumentException();

		this.damage = damage;
	}
	
	public void destroy() {
		isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
}
