package fly2.game.logic;

import fly2.phyzix.*;
import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class WorldUpdaterTest extends TestCase {

	private WorldUpdater worldUpdater;
	private World world;

	public void setUp() {
		world = new World(100.0, 200.0);
		worldUpdater = new WorldUpdater(world);
	}

	public void testBulletDamage() {
		world.registerImpactStrategy(new PlaneBulletImpactStrategy());
		world.setRaiseErrorIfImpactStrategyNotFound(true);

		Weapon weapon = new Weapon(world);
		weapon.setBulletDirection(UP);
		weapon.setBulletSize(2.0);
		weapon.setBulletSpeed(4.0);
		weapon.setBulletDamage(4);
		weapon.setPosition(10.0, 10.0);

		Plane plane1 = new Plane(weapon);
		plane1.setPosition(10.0, 0.0);
		plane1.setDirection(UP);
		plane1.setSpeed(0);
		plane1.setSize(10.0, 10.0);
		plane1.setHealth(10);
		world.addItem(plane1);

		Plane plane2 = new Plane(weapon);
		plane2.setPosition(10.0, 50.0);
		plane2.setDirection(DOWN);
		plane2.setSpeed(0);
		plane2.setSize(10.0, 10.0);
		plane2.setHealth(10);
		world.addItem(plane2);

		assertEquals(2, world.getItemsCount());

		plane1.fire();

		assertEquals(3, world.getItemsCount());

		// скорость пули 4 => через 9 шагов она еще не ударилась
		// во второй самолет
		for (int i = 0; i < 9; i++)
			worldUpdater.update();

		assertEquals(3, world.getItemsCount());
		assertEquals(10, plane1.getHealth());
		assertEquals(10, plane2.getHealth());

		// 10-ый шаг -> пуля ударяется в самолет
		worldUpdater.update();

		assertEquals(2, world.getItemsCount());
		assertEquals(10, plane1.getHealth());
		assertEquals(6, plane2.getHealth());
	}

	public void testPlaneDestroy() {
		world.registerImpactStrategy(new PlaneBulletImpactStrategy());

		Weapon weapon = new Weapon(world);
		weapon.setBulletDirection(UP);
		weapon.setBulletSize(2.0);
		weapon.setBulletSpeed(4.0);
		weapon.setBulletDamage(5);
		weapon.setPosition(10.0, 10.0);

		Plane plane1 = new Plane(weapon);
		plane1.setPosition(10.0, 0.0);
		plane1.setDirection(UP);
		plane1.setSpeed(0);
		plane1.setSize(10.0, 10.0);
		plane1.setHealth(10);
		world.addItem(plane1);

		Plane plane2 = new Plane(weapon);
		plane2.setPosition(10.0, 50.0);
		plane2.setDirection(DOWN);
		plane2.setSpeed(0);
		plane2.setSize(10.0, 10.0);
		plane2.setHealth(10);
		world.addItem(plane2);

		plane1.fire();
		plane1.fire();

		for (int i = 0; i < 10; i++)
			worldUpdater.update();

		assertEquals(1, world.getItemsCount());
		assertEquals(10, plane1.getHealth());
		assertEquals(0, plane2.getHealth());
		assertTrue(plane2.isDestroyed());
	}

	public void testBulletOutOfWorld() {
		Weapon weapon = new Weapon(world);
		weapon.setBulletDirection(UP);
		weapon.setBulletSize(2.0);
		weapon.setBulletSpeed(20.0);
		weapon.setBulletDamage(4);
		weapon.setPosition(10.0, 10.0);

		Plane plane1 = new Plane(weapon);
		plane1.setPosition(10.0, 0.0);
		plane1.setDirection(UP);
		plane1.setSpeed(0);
		plane1.setSize(10.0, 10.0);
		plane1.setHealth(10);
		world.addItem(plane1);

		assertEquals(1, world.getItemsCount());

		plane1.fire();

		assertEquals(2, world.getItemsCount());

		for (int i = 0; i < 9; i++)
			worldUpdater.update();

		assertEquals(2, world.getItemsCount());

		worldUpdater.update();

		assertEquals(1, world.getItemsCount());
	}

	public void testNullWorld() {
		try {
			new WorldUpdater(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
