package fly2.game.enemy;

public final class NullEnemyBrain implements EnemyBrain {

	public void setContext(EnemyBrainContext context) {
		throw new EnemyBrainNotInitializeException();
	}

	public StepResult activate() {
		throw new EnemyBrainNotInitializeException();
	}
}
