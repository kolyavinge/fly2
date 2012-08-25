package fly2.model;

public class DefaultPlaneFactory implements PlaneFactory {

	private WeaponFactory weaponFactory;

	public DefaultPlaneFactory(WeaponFactory weaponFactory) {
		this.weaponFactory = weaponFactory;
	}

	public Plane makePlayerPlane() {
		Plane playerPlane = new DefaultPlane(weaponFactory.makeGun());

		return playerPlane;
	}
}
