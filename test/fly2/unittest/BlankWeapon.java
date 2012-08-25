package fly2.unittest;

import fly2.gamemodel.Weapon;

public class BlankWeapon implements Weapon {

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}

	public int getBulletDamage() {
		return 0;
	}

	public double getBulletSize() {
		return 0;
	}

	public double getBulletSpeed() {
		return 0;
	}

	public void fire() {
	}

	public void moveX(double value) {
	}

	public void moveY(double value) {
	}
}
