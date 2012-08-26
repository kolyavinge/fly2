package fly2.game.logic;

import fly2.game.frontend.GameWorld;
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
