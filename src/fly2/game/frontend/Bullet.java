package fly2.game.frontend;


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
