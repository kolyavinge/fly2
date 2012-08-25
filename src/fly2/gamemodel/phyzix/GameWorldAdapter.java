package fly2.gamemodel.phyzix;

import fly2.gamemodel.GameWorld;
import fly2.phyzix.World;

public class GameWorldAdapter implements GameWorld {

	private World world;

	public GameWorldAdapter(World world) {
		if (world == null)
			throw new NullPointerException();
		
		this.world = world;
	}

	public double getWidth() {
		return world.getWidth();
	}

	public double getHeight() {
		return world.getHeight();
	}
}
