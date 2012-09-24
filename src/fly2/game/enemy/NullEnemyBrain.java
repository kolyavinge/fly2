package fly2.game.enemy;

public class NullEnemyBrain implements EnemyBrain {

	public void setContext(EnemyBrainContext context) {
		throw new EnemyBrainNotInitializeException();
	}

	public StepResult activate() {
		throw new EnemyBrainNotInitializeException();
	}
}
