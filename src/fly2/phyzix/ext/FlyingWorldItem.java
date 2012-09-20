package fly2.phyzix.ext;

import fly2.common.Direction;
import fly2.phyzix.Updateable;
import fly2.phyzix.WorldItem;
import static fly2.common.Direction.*;

/**
 * Летaющий игровой объект.
 * Отличается от простого WorldItem-a тем, что имеет скорость и направление движения.
 */
public class FlyingWorldItem extends WorldItem implements Updateable {

	private double flySpeed;
	private Direction flyDirection;

	public FlyingWorldItem() {
		this.flyDirection = _UNDEFINED;
	}

	public void update() {
		switch (flyDirection) {
		case LEFT:
			moveX(-flySpeed);
			break;
		case RIGHT:
			moveX(flySpeed);
			break;
		case UP:
			moveY(flySpeed);
			break;
		case DOWN:
			moveY(-flySpeed);
			break;
		case _UNDEFINED:
			throw new IllegalArgumentException("Direction was undefined. Type: " + getClass().getName());
		default:
			throw new IllegalArgumentException("direction");
		}
	}

	public double getFlySpeed() {
		return flySpeed;
	}

	public void setFlySpeed(double speed) {
		if (speed < 0)
			throw new IllegalArgumentException("speed");

		this.flySpeed = speed;
	}

	public Direction getFlyDirection() {
		return flyDirection;
	}

	public void setFlyDirection(Direction direction) {
		if (direction == null)
			throw new NullPointerException("direction");

		this.flyDirection = direction;
	}
}
