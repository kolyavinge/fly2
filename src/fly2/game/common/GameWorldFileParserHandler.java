package fly2.game.common;

import fly2.game.logic.PlaneFactory;

public interface GameWorldFileParserHandler {

	void createGameWorld(double width, double height);

	void createPlaneFactory(PlaneFactory factory);
	
	void createEnemyPlane(double x, double y);
}
