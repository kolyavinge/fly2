package fly2.game.frontend;

public final class GameModelFactory {

	private static final String gameModelClassPropName = "fly2.gameModelClassName";
	private static final GameModelFactory instance;

	static {
		instance = new GameModelFactory();
	}

	public static GameModelFactory getInstance() {
		return instance;
	}

	public static String getGameModelClassPropName() {
		return gameModelClassPropName;
	}

	private GameModel gameModel;

	private GameModelFactory() {
	}

	public GameModel createModel() {
		String gameModelClassName = System.getProperty(gameModelClassPropName);
		try {
			gameModel = (GameModel) Class.forName(gameModelClassName).newInstance();
		} catch (Exception exp) {
			throw new IllegalArgumentException();
		}

		return gameModel;
	}
}
