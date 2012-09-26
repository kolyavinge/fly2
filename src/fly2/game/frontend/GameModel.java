package fly2.game.frontend;

import java.util.Collection;

/**
 * Модель игры
 */
public interface GameModel {

	GameWorld getWorld();

	Plane getPlayerPlane();

	Collection<Plane> getEnemyPlanes();

	int getEnemyPlanesCount();

	Collection<Bullet> getBullets();

	int getBulletsCount();
	
	PlayerPlaneActions getPlayerPlaneActions();
	
	void update();
}
