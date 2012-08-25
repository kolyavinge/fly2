package fly2.gamemodel.phyzix;

import fly2.phyzix.*;
import fly2.phyzix.ext.MoveableWorldItem;

/**
 * Пуля
 */
public class BulletWorldItem extends MoveableWorldItem implements Destroyable {

	private int damage;
	private boolean isDestroyed;

	public BulletWorldItem() {
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
