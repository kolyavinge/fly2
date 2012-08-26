package fly2.phyzix.ext;

import fly2.common.Direction;
import fly2.phyzix.Updateable;
import fly2.phyzix.WorldItem;
import static fly2.common.Direction.*;

/**
 * Перемащаемый игровой объект.
 * Отличается от простого WorldItem-a тем, что имеет скорость и направление движения.
 */
public class MoveableWorldItem extends WorldItem implements Updateable {

	private double speed;
	private Direction direction;

	public MoveableWorldItem() {
		this.direction = _UNDEFINED;
	}

	public void update() {
		switch (direction) {
		case LEFT:
			moveX(-speed);
			break;
		case RIGHT:
			moveX(speed);
			break;
		case UP:
			moveY(speed);
			break;
		case DOWN:
			moveY(-speed);
			break;
		case _UNDEFINED:
			throw new IllegalArgumentException("Direction was undefined. Type: " + getClass().getName());
		default:
			throw new IllegalArgumentException("direction");
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		if (speed < 0)
			throw new IllegalArgumentException("speed");

		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		if (direction == null)
			throw new NullPointerException("direction");

		this.direction = direction;
	}
}
