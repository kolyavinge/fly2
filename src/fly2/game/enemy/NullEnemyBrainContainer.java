package fly2.game.enemy;

import fly2.game.frontend.PlaneKind;

final class NullEnemyBrainContainer implements EnemyBrainContainer {

	public EnemyBrain getBrain(PlaneKind planeKind) {
		throw new IllegalStateException("EnemyBrainContainer not initialize");
	}
}
