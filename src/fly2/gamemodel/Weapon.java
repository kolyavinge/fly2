package fly2.gamemodel;

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
