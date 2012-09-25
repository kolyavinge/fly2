package fly2.game.logic;

import fly2.phyzix.World;
import fly2.phyzix.ext.ReturnedOutOfWorldStrategy;

import java.util.Collection;

public final class GameWorld implements fly2.game.frontend.GameWorld, PlaneListener {

	private World world;
	private Plane playerPlane;

	public GameWorld(double width, double height) {
		initWorld(width, height);
	}

	private void initWorld(double width, double height) {
		world = new World(width, height);
		world.registerImpactStrategy(new PlaneBulletImpactStrategy());
		world.registerImpactStrategy(new PlanePlaneImpactStrategy());
	}

	public Plane getPlayerPlane() {
		return playerPlane;
	}

	public void setPlayerPlane(Plane player) {
		playerPlane = player;
		// ставим игрока по-середине экрана
		playerPlane.setPosition((world.getWidth() - playerPlane.getWidth()) / 2.0, 0.0);
		playerPlane.setListener(this);
		world.registerOutOfWorldStrategy(playerPlane, new ReturnedOutOfWorldStrategy<Plane>());
		world.addItem(playerPlane);
	}

	public void addEnemyPlane(Plane enemy) {
		enemy.setListener(this);
		world.registerOutOfWorldStrategy(enemy, new ReturnedOutOfWorldStrategy<Plane>());
		world.addItem(enemy);
	}

	public void update() {
		world.updateItems();
		world.activateOutOfWorldItems();
		world.activateItemsImpact();
		world.removeDestroyedItems();
	}

	/* PlaneListener */
	public void onMoveLeft(Plane plane) {
		world.activateOutOfWorld(plane);
	}

	public void onMoveRight(Plane plane) {
		world.activateOutOfWorld(plane);
	}

	public void onFire(Plane plane, Bullet bullet) {
		world.addItem(bullet);
	}
	/* PlaneListener */

	public double getWidth() {
		return world.getWidth();
	}

	public double getHeight() {
		return world.getHeight();
	}

	public Collection<Plane> getEnemyPlanes() {
		return WorldItemsHelper.getEnemyPlanes(world.getItems());
	}

	public int getEnemyPlanesCount() {
		return WorldItemsHelper.getEnemyPlanesCount(world.getItems());
	}

	public Collection<Bullet> getBullets() {
		return WorldItemsHelper.getBullets(world.getItems());
	}

	public int getBulletsCount() {
		return WorldItemsHelper.getBulletsCount(world.getItems());
	}
}
