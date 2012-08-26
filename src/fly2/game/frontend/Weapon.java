package fly2.game.frontend;

public interface Weapon {

	double getX();

	double getY();

	int getBulletDamage();

	double getBulletSize();

	double getBulletSpeed();

	void fire();

	void moveX(double value);

	void moveY(double value);
}
