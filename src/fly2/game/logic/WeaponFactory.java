package fly2.game.logic;

public final class WeaponFactory {

	private static final WeaponFactory instance = new WeaponFactory();

	public static WeaponFactory getInstance() {
		return instance;
	}

	private WeaponFactory() {
	}

	public Weapon makeGun() {
		Weapon gun = new Weapon();
		gun.setBulletDamage(1);
		gun.setBulletSize(0.5);
		gun.setBulletSpeed(1.0);

		return gun;
	}
}
