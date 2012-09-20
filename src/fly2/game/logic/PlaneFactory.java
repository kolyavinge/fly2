package fly2.game.logic;

import static fly2.common.Direction.*;

public final class PlaneFactory {

	private static final PlaneFactory instance = new PlaneFactory();

	public static PlaneFactory getInstance() {
		return instance;
	}

	private WeaponFactory weaponFactory = WeaponFactory.getInstance();

	private PlaneFactory() {
	}

	public Plane makePlayer() {
		Weapon gun = weaponFactory.makeGun();

		Plane player = new Plane(gun);
		player.setSize(2.0, 1.5);
		player.setFlyDirection(UP);
		player.setHealth(10);
		player.setFlySpeed(0.01);

		gun.setBulletDirection(UP);
		gun.setPosition(player.getMiddleX(), player.getY() + player.getHeight());

		return player;
	}

	public Plane makeEnemy() {
		Weapon gun = weaponFactory.makeGun();

		Plane enemy = new Plane(gun);
		enemy.setSize(2.0, 1.5);
		enemy.setFlyDirection(DOWN);
		enemy.setHealth(1);
		enemy.setFlySpeed(0.01);

		gun.setBulletDirection(DOWN);
		gun.setPosition(enemy.getMiddleX(), enemy.getY() + enemy.getHeight());

		return enemy;
	}
}
