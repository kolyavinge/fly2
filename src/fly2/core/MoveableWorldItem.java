package fly2.core;

import fly2.common.Direction;

/**
 * Перемащаемый игровой объект. Отличается от простого WorldItem-a тем, что имеет
 * дополнительно скорость и направление движения.
 */
public class MoveableWorldItem extends WorldItem implements Updateable {

	private double speed;
	private Direction direction;

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
		default:
			throw new IllegalArgumentException();
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		if (speed < 0)
			throw new IllegalArgumentException();

		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
