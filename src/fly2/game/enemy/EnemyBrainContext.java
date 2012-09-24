package fly2.game.enemy;

import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;

import java.util.Collection;

public interface EnemyBrainContext {

	GameWorld getGameWorld();

	Plane getPlayer();

	Plane getEnemy();

	Collection<Plane> getEnemyPlanes();

	Collection<Bullet> getBullets();
}
