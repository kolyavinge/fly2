package fly2.model;

import fly2.core.*;
import junit.framework.TestCase;

public class DefaultWeaponFactoryTest extends TestCase {

	private DefaultWeaponFactory weaponFactory;
	private WorldItemCollection worldItemCollection;

	public void setUp() {
		worldItemCollection = getWorldItemCollection();
		weaponFactory = new DefaultWeaponFactory(worldItemCollection);
	}

	public void testMakeGun() {
		Weapon gun = weaponFactory.makeGun();
		assertEquals(0.0, gun.getX(), 0.001);
		assertEquals(0.0, gun.getY(), 0.001);
		assertEquals(2, gun.getBulletDamage());
		assertEquals(6.0, gun.getBulletSize(), 0.001);
		assertEquals(10.0, gun.getBulletSpeed(), 0.001);
	}

	private WorldItemCollection getWorldItemCollection() {
		return new WorldItemCollection() {

			public void addItem(WorldItem item) {
			}

			public void removeItem(WorldItem item) {
			}

			public Iterable<WorldItem> getItems() {
				return null;
			}

			public int getItemsCount() {
				return 0;
			}
		};
	}
}
