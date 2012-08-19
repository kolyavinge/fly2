package fly2.model;

import fly2.core.*;
import fly2.unittest.*;
import junit.framework.TestCase;

public class PlaneTest extends TestCase {

	private double width = 10.0;
	private double height = 20.0;
	private Plane plane;
	private Weapon weapon;
	private WorldItemCollection worldItems;

	public void setUp() {
		worldItems = new TestWorldItemCollection();
		weapon = new Weapon(worldItems);
		plane = new Plane(weapon);
		plane.setSize(width, height);
	}

	public void testFire() {
		plane.setWeapon(weapon);
		plane.fire();
		assertEquals(1, worldItems.getItemsCount());
		WorldItem item = getFirstItem(worldItems);
		assertTrue(item instanceof Bullet);
		// убеждаемся что пулька создалась, все остальное
		// протестировано в классе Weapon
	}

	public void testCreateWithNullWeapon() {
		try {
			plane.setWeapon(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullWeapon() {
		try {
			plane = new Plane(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private WorldItem getFirstItem(WorldItemCollection itemCollection) {
		return itemCollection.getItems().iterator().next();
	}
}
