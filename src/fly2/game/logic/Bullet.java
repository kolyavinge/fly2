package fly2.game.logic;

import fly2.phyzix.ext.MoveableWorldItem;

/**
 * Пуля
 */
public class Bullet extends MoveableWorldItem implements fly2.game.frontend.Bullet {

	private int damage;
	private boolean isDestroyed;
	private int ownerPlaneId;

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

	public int getOwnerPlaneId() {
		return ownerPlaneId;
	}

	public void setOwnerPlaneId(int ownerPlaneId) {
		this.ownerPlaneId = ownerPlaneId;
	}
}
