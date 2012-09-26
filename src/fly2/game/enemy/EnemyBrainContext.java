package fly2.game.enemy;

import fly2.game.frontend.*;
import java.util.Collection;

public interface EnemyBrainContext {

	GameWorld getGameWorld();

	Plane getPlayer();

	Plane getEnemy();

	PlaneActions getEnemyActions();

	Collection<Plane> getEnemyPlanes();

	Collection<Bullet> getBullets();
}
