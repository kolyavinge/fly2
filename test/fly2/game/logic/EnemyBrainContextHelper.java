package fly2.game.logic;

import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.EnemyBrainContextBuilder;

import java.util.Collections;

public class EnemyBrainContextHelper {

	public static EnemyBrainContext createContextForEnemy(Plane enemy, GameWorld gameWorld) {
		EnemyBrainContextBuilder builder = new EnemyBrainContextBuilder();
		builder.setGameWorld(gameWorld);
		builder.setPlayer(gameWorld.getPlayerPlane());
		builder.setEnemy(enemy);
		builder.setEnemyPlanes(Collections.<fly2.game.frontend.Plane> unmodifiableCollection(gameWorld.getEnemyPlanes()));
		builder.setBullets(Collections.<fly2.game.frontend.Bullet> unmodifiableCollection(gameWorld.getBullets()));
		
		return builder.build();
	}
}
