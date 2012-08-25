package fly2.model;

import fly2.core.*;

public interface Plane extends Updateable, Destroyable {

	Weapon getWeapon();
	void setWeapon(Weapon weapon);
	
	int getHealth();
	void setHealth(int health);

	void damage(int damageValue);

	void fire();

	void moveX(double value);
	void moveY(double value);
}
