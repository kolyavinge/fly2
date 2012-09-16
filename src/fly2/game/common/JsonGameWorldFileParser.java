package fly2.game.common;

import org.json.*;

public class JsonGameWorldFileParser extends GameWorldFileParser {

	private String fileContent;

	public JsonGameWorldFileParser(String fileContent) {
		if (fileContent == null)
			throw new NullPointerException("fileContent");

		this.fileContent = fileContent;
	}

	protected void tryParse(GameWorldFileParserHandler handler) throws Exception {
		JSONObject root = new JSONObject(fileContent);

		JSONObject world = root.getJSONObject("world");
		double gameWorldWidth = world.getDouble("width");
		double gameWorldHeight = world.getDouble("height");
		handler.createGameWorld(gameWorldWidth, gameWorldHeight);

		JSONArray enemies = world.getJSONArray("enemies");
		for (int i = 0; i < enemies.length(); i++) {
			JSONObject enemy = enemies.getJSONObject(i);
			double enemyX = enemy.getDouble("x");
			double enemyY = enemy.getDouble("y");
			handler.createEnemyPlane(enemyX, enemyY);
		}
	}
}
