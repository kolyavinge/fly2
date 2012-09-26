package fly2.game.enemy;

import fly2.game.frontend.PlaneKind;

public class NormalEnemyBrainContainer implements EnemyBrainContainer {

	private EnemyBrain brain = new NormalEnemyBrain();
	
	public EnemyBrain getBrain(PlaneKind planeKind) {
		switch (planeKind) {
		case ENEMY:
			return brain;
		default:
			throw new IllegalArgumentException("planeKind");
		}
	}
}
