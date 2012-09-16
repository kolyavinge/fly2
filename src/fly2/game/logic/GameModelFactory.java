package fly2.game.logic;

import fly2.game.common.ResourceFileReader;

public final class GameModelFactory {

	private static final GameModelFactory instance;

	static {
		instance = new GameModelFactory();
	}

	public static GameModelFactory getInstance() {
		return instance;
	}

	private GameModelFactory() {
	}

	public fly2.game.frontend.GameModel createModel(ResourceFileReader fileReader) {
		return new GameModel(fileReader);
	}
}
