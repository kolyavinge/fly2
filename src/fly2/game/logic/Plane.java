package fly2.game.logic;

import fly2.phyzix.ext.FlyingWorldItem;

public class Plane extends FlyingWorldItem implements fly2.game.frontend.Plane {

	// TODO: генерацию id-шника можно вынести в отдельный класс
	private static int lastId = 1;

	private double moveSpeed;
	private int health;
	private Weapon weapon;
	private PlaneListener listener = new DefaultPlaneListener();
	private int id = lastId++;

	public Plane(Weapon weapon) {
		setWeapon(weapon);
	}

	public Plane() {
		weapon = new NullWeapon();
	}

	public int getId() {
		return id;
	}

	public PlaneListener getListener() {
		return listener;
	}

	public void setListener(PlaneListener listener) {
		if (listener == null)
			throw new NullPointerException("listener");

		this.listener = listener;
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

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		if (moveSpeed < 0)
			throw new IllegalArgumentException("moveSpeed");
		
		this.moveSpeed = moveSpeed;
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
		Bullet bullet = weapon.fire();
		listener.onFire(this, bullet);
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
	protected void moveX(double value) {
		super.moveX(value);

		if (weapon != null)
			weapon.moveX(value);

		if (value < 0) {
			listener.onMoveLeft(this);
		} else if (value > 0) {
			listener.onMoveRight(this);
		}
	}

	@Override
	protected void moveY(double value) {
		super.moveY(value);
		if (weapon != null)
			weapon.moveY(value);
	}

	public void moveLeft() {
		moveX(-moveSpeed);
	}

	public void moveRight() {
		moveX(moveSpeed);
	}
}
