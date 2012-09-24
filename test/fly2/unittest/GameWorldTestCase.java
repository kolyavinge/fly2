package fly2.unittest;

import fly2.game.frontend.Direction;
import fly2.game.logic.GameWorld;
import fly2.game.logic.Plane;
import fly2.game.logic.Weapon;
import junit.framework.TestCase;

public class GameWorldTestCase extends TestCase {

	protected GameWorld gameWorld;
	
	protected void createWorld(double width, double height) {
		gameWorld = new GameWorld(width, height);
	}
	
	protected Plane createPlayer() {
		Plane player = new Plane();
		player.setFlyDirection(Direction.UP);
		Weapon weapon = new Weapon();
		weapon.setBulletDirection(player.getFlyDirection());
		player.setWeapon(weapon);
		gameWorld.setPlayerPlane(player);
		
		return player;
	}
	
	protected Plane createEnemy() {
		Plane enemy = new Plane();
		enemy.setFlyDirection(Direction.DOWN);
		Weapon weapon = new Weapon();
		weapon.setBulletDirection(enemy.getFlyDirection());
		enemy.setWeapon(weapon);
		gameWorld.addEnemyPlane(enemy);
		
		return enemy;
	}
}
