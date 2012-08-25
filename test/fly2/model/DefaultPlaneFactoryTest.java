package fly2.model;

import junit.framework.TestCase;

public class DefaultPlaneFactoryTest extends TestCase {

	private DefaultPlaneFactory planeFactory;
	private WeaponFactory weaponFactory;
	private Weapon weapon;
	
	public void setUp() {
		weapon = getWeapon();
		weaponFactory = getWeaponFactory();
		planeFactory = new DefaultPlaneFactory(weaponFactory);
	}
	
	public void testMakePlayerPlane() {
		Plane playerPlane = planeFactory.makePlayerPlane();
		assertSame(weapon, playerPlane.getWeapon());
	}
	
	private WeaponFactory getWeaponFactory() {
		return new WeaponFactory() {
			public Weapon makeGun() {
				return weapon;
			}
		};
	}
	
	private Weapon getWeapon() {
		return new Weapon() {

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
		};
	}
}
