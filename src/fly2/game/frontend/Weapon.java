package fly2.game.frontend;


public interface Weapon {

	double getX();

	double getY();
	
	Direction getBulletDirection();

	int getBulletDamage();

	double getBulletSize();

	double getBulletSpeed();
}
