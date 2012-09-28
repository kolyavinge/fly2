package fly2.game.frontend;

import java.util.Collection;

public interface GameModel {

	GameWorld getWorld();

	Plane getPlayerPlane();

	Collection<Plane> getEnemyPlanes();

	Collection<Bullet> getBullets();

	PlaneActions getPlayerPlaneActions();
	
	void update();
}
