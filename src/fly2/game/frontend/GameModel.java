package fly2.game.frontend;

/**
 * Модель игры
 */
public interface GameModel {

	GameWorld getWorld();

	Plane getPlayerPlane();

	Iterable<Plane> getEnemyPlanes();

	int getEnemyPlanesCount();

	Iterable<Bullet> getBullets();

	int getBulletsCount();
	
	PlayerPlaneActions getPlayerPlaneActions();
	
	void update();
}
