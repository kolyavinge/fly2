package fly2.game.logic;

import fly2.common.android.ResourceFileReader;
import fly2.game.common.GameWorldFileParser;
import fly2.game.common.GameWorldFileParserHandler;
import fly2.game.common.JsonGameWorldFileParser;

import java.io.IOException;

public class GameWorldFactory implements GameWorldFileParserHandler {

	private static final int MAX_WORLDS_COUNT = 1;

	private GameWorld gameWorld;
	private ResourceFileReader reader;

	public GameWorldFactory(ResourceFileReader reader) {
		if (reader == null)
			throw new NullPointerException("reader");

		this.reader = reader;
	}

	public int getWorldsCount() {
		return MAX_WORLDS_COUNT;
	}

	public GameWorld makeWorld(int worldNumber) {
		verifyWorldNumber(worldNumber);

		String fileContent = getGameWorldFileContent(worldNumber);
		parseGameWorldFile(fileContent);

		return gameWorld;
	}

	private void verifyWorldNumber(int worldNumber) {
		if (worldNumber < 0)
			throw new GameWorldFactoryException("Negative world number");

		if (worldNumber >= MAX_WORLDS_COUNT)
			throw new GameWorldFactoryException("No world with number=" + Integer.toString(worldNumber));
	}

	private String getGameWorldFileContent(int worldNumber) {
		String fileName = getGameWorldFileName(worldNumber);
		try {
			return reader.getFileText(fileName);
		} catch (IOException exp) {
			throw new GameWorldFactoryException("Can't reader file " + fileName);
		}
	}

	private void parseGameWorldFile(String fileContent) {
		GameWorldFileParser parser = new JsonGameWorldFileParser(fileContent);
		parser.setHandler(this);
		parser.parse();
	}

	private String getGameWorldFileName(int worldNumber) {
		return "world_" + Integer.toString(worldNumber);
	}

	/* GameWorldFileParserHandler */

	public void createGameWorld(double width, double height) {
		gameWorld = new GameWorld(width, height);
	}

	public void createEnemyPlane(double x, double y) {
		if (gameWorld == null)
			throw new GameWorldFactoryException("World was not created");

		Plane enemy = gameWorld.createEnemyPlane();
		enemy.setPosition(x, y);
	}
}
