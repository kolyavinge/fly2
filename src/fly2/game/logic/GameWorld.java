package fly2.game.logic;

import fly2.phyzix.World;

public class GameWorld {

	private World phyzixWorld;

	public GameWorld(double width, double height) {
		phyzixWorld = new World(width, height);
	}
	
	public double getWidth() {
		return phyzixWorld.getWidth();
	}
	
	public double getHeight() {
		return phyzixWorld.getHeight();
	}
}
