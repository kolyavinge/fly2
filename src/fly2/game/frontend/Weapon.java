package fly2.game.frontend;

import fly2.common.Direction;

public interface Weapon {

	double getX();

	double getY();
	
	Direction getBulletDirection();

	int getBulletDamage();

	double getBulletSize();

	double getBulletSpeed();
}
