package fly2.unittest.stub;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContainer;
import fly2.game.frontend.PlaneKind;

import java.util.HashMap;
import java.util.Map;

public class TestEnemyBrainContainer implements EnemyBrainContainer {

	private Map<PlaneKind, EnemyBrain> brains = new HashMap<PlaneKind, EnemyBrain>();

	public void addBrain(PlaneKind planeKind, EnemyBrain enemyBrain) {
		brains.put(planeKind, enemyBrain);
	}

	public EnemyBrain getBrain(PlaneKind planeKind) {
		return brains.get(planeKind);
	}
}
