package fly2.game.logic;

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

	public fly2.game.frontend.GameModel createModel() {
		return new GameModel();
	}
}
