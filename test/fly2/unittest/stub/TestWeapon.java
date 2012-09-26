package fly2.unittest.stub;

import fly2.game.frontend.Direction;
import fly2.game.frontend.Weapon;

public class TestWeapon implements Weapon {

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}

	public Direction getBulletDirection() {
		return null;
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
}
