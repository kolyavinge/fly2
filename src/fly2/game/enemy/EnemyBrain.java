package fly2.game.enemy;

public interface EnemyBrain {

	void setContext(EnemyBrainContext context);
	
	StepResult activate();
}
