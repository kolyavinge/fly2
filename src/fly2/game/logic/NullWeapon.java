package fly2.game.logic;

public class NullWeapon extends Weapon {

	@Override
	public Bullet fire() {
		throw new IllegalStateException("Plane weapon was null");
	}
}
