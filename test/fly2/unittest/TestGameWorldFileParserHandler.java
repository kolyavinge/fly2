package fly2.unittest;

import fly2.common.Tuple;
import fly2.game.common.GameWorldFileParserHandler;

import java.util.ArrayList;

public class TestGameWorldFileParserHandler implements GameWorldFileParserHandler {

	private double gameWorldWidth, gameWorldHeight;
	private ArrayList<Tuple<Double, Double>> enemies;

	public TestGameWorldFileParserHandler() {
		enemies = new ArrayList<Tuple<Double, Double>>();
	}

	public double getGameWorldWidth() {
		return gameWorldWidth;
	}

	public double getGameWorldHeight() {
		return gameWorldHeight;
	}

	public ArrayList<Tuple<Double, Double>> getEnemies() {
		return enemies;
	}

	/* GameWorldFileParserHandler */

	public void createGameWorld(double width, double height) {
		gameWorldWidth = width;
		gameWorldHeight = height;
	}

	public void createEnemyPlane(double x, double y) {
		Tuple<Double, Double> t = new Tuple<Double, Double>(x, y);
		enemies.add(t);
	}

	/* GameWorldFileParserHandler */
}
