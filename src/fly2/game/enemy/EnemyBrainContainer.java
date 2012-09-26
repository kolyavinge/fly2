package fly2.game.enemy;

import fly2.game.frontend.PlaneKind;

public interface EnemyBrainContainer {

	EnemyBrain getBrain(PlaneKind planeKind);
}
