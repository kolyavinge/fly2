package fly2.game.logic;

import static fly2.common.Direction.*;
import fly2.common.Direction;
import fly2.phyzix.WorldItemCollection;

/**
 * Оружие для самолета
 */
public class Weapon implements fly2.game.frontend.Weapon {

	private double x, y;
	private double bulletSize;
	private double bulletSpeed;
	private int bulletDamage;
	private Direction bulletDirection;
	private WorldItemCollection worldItems;
	private int ownerPlaneId;

	public Weapon(WorldItemCollection worldItems) {
		if (worldItems == null)
			throw new NullPointerException("worldItems");

		this.worldItems = worldItems;
		this.bulletSize = 1.0;
		this.bulletDirection = _UNDEFINED;
	}

	/**
	 * Создает в игровом мире новую пульку с заданными параметрами.
	 */
	public void fire() {
		Bullet bullet = new Bullet();
		bullet.setPosition(x - bulletSize / 2.0, y - bulletSize / 2.0);
		bullet.setSize(bulletSize, bulletSize);
		bullet.setDirection(bulletDirection);
		bullet.setSpeed(bulletSpeed);
		bullet.setDamage(bulletDamage);
		bullet.setOwnerPlaneId(ownerPlaneId);
		worldItems.addItem(bullet);
	}

	public void moveX(double value) {
		x += value;
	}

	public void moveY(double value) {
		y += value;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}

	public double getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(double bulletSize) {
		this.bulletSize = bulletSize;
	}

	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(double bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getBulletDamage() {
		return bulletDamage;
	}

	public void setBulletDamage(int bulletDamage) {
		this.bulletDamage = bulletDamage;
	}

	public Direction getBulletDirection() {
		return bulletDirection;
	}

	public void setBulletDirection(Direction bulletDirection) {
		this.bulletDirection = bulletDirection;
	}

	public WorldItemCollection getWorldItems() {
		return worldItems;
	}

	public int getOwnerPlaneId() {
		return ownerPlaneId;
	}

	public void setOwnerPlaneId(int ownerPlaneId) {
		this.ownerPlaneId = ownerPlaneId;
	}
}
