package fly2.game.frontend;

import fly2.phyzix.*;

public interface Plane extends Updateable, Destroyable {

	double getX();

	double getY();

	double getMiddleX();

	double getMiddleY();

	int getHealth();

	Weapon getWeapon();
}
