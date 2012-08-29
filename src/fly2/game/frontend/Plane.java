package fly2.game.frontend;

public interface Plane {

	double getX();

	double getY();

	double getMiddleX();

	double getMiddleY();
	
	double getWidth();
	
	double getHeight();

	int getHealth();

	Weapon getWeapon();
	
	boolean isDestroyed();
}
