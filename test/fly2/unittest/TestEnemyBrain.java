package fly2.unittest;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.StepResult;

public class TestEnemyBrain implements EnemyBrain {

	private StepResult stepResult;

	public StepResult getStepResult() {
		return stepResult;
	}

	public void setStepResult(StepResult stepResult) {
		this.stepResult = stepResult;
	}

	public void setContext(EnemyBrainContext context) {
	}

	public StepResult activate() {
		return stepResult;
	}
}
