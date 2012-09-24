package fly2.game.logic;

import static fly2.game.frontend.Direction.*;
import static fly2.game.frontend.PlaneKind.*;

public final class GamePlaneFactory implements PlaneFactory {

	private WeaponFactory weaponFactory = WeaponFactory.getInstance();

	public Plane makePlayer() {
		Weapon gun = weaponFactory.makeGun();

		Plane player = new Plane();
		player.setKind(PLAYER);
		player.setSize(2.0, 1.5);
		player.setFlyDirection(UP);
		player.setHealth(10);
		player.setFlySpeed(0.01);
		player.setMoveSpeed(1.0);
		player.setWeapon(gun);

		gun.setBulletDirection(UP);
		gun.setPosition(player.getMiddleX(), player.getY() + player.getHeight());

		return player;
	}

	public Plane makeEnemy() {
		Weapon gun = weaponFactory.makeGun();

		Plane enemy = new Plane();
		enemy.setKind(ENEMY);
		enemy.setSize(2.0, 1.5);
		enemy.setFlyDirection(DOWN);
		enemy.setHealth(1);
		enemy.setFlySpeed(0.01);
		enemy.setMoveSpeed(0.5);
		enemy.setWeapon(gun);

		gun.setBulletDirection(DOWN);
		gun.setPosition(enemy.getMiddleX(), enemy.getY() + enemy.getHeight());

		return enemy;
	}
}
