package fly2.game.frontend;

import fly2.common.Direction;

public interface Bullet {

	double getX();

	double getY();

	double getMiddleX();

	double getMiddleY();

	double getWidth();

	double getHeight();

	boolean isDestroyed();

	Direction getFlyDirection();

	int getDamage();

	double getFlySpeed();
}
