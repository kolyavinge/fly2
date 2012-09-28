package fly2.unittest.stub;

import fly2.game.logic.GameWorld;
import fly2.game.logic.GameWorldFactory;

import java.util.HashMap;
import java.util.Map;

public class TestGameWorldFactory implements GameWorldFactory {

	private Map<Integer, GameWorld> worlds = new HashMap<Integer, GameWorld>();
	
	public void addWorld(int number, GameWorld world) {
		worlds.put(number, world);
	}
	
	public GameWorld makeWorld(int worldNumber) {
		return worlds.get(worldNumber);
	}
}
