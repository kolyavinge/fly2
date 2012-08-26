package fly2.game.logic;

import fly2.phyzix.World;

public class WorldUpdater {

	private World world;

	public WorldUpdater(World world) {
		if (world == null)
			throw new NullPointerException("world");

		this.world = world;
	}

	public void update() {
		world.updateItems();
		world.activateOutOfWorldItems();
		world.activateItemsImpact();
		world.removeDestroyedItems();
	}
}
