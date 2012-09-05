package fly2.game.logic;

import static fly2.common.Direction.*;

public class PlaneFactory {

	private WeaponFactory weaponFactory;

	public PlaneFactory(WeaponFactory weaponFactory) {
		if (weaponFactory == null)
			throw new NullPointerException("weaponFactory");

		this.weaponFactory = weaponFactory;
	}

	public Plane makePlayer() {
		Weapon gun = weaponFactory.makeGun();

		Plane player = new Plane(gun);
		player.setSize(3.5, 3.0);
		player.setDirection(UP);
		player.setHealth(10);

		gun.setBulletDirection(UP);
		gun.setPosition(player.getMiddleX(), player.getY() + player.getHeight());

		return player;
	}

	public Plane makeEnemy() {
		Weapon gun = weaponFactory.makeGun();

		Plane enemy = new Plane(gun);
		enemy.setSize(3.5, 3.0);
		enemy.setDirection(DOWN);
		enemy.setHealth(1);

		gun.setBulletDirection(DOWN);
		gun.setPosition(enemy.getMiddleX(), enemy.getY() + enemy.getHeight());

		return enemy;
	}
}
