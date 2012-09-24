package fly2.game.logic;

import fly2.game.enemy.EnemyBrain;
import fly2.game.enemy.EnemyBrainContext;
import fly2.game.enemy.NullEnemyBrain;
import fly2.game.enemy.StepResult;

public class EnemyPlane extends Plane {

	private EnemyBrain brain = new NullEnemyBrain();

	public void setBrain(EnemyBrain brain) {
		if (brain == null)
			throw new NullPointerException();

		this.brain = brain;
	}

	public void setBrainContext(EnemyBrainContext context) {
		if (context == null)
			throw new NullPointerException();

		brain.setContext(context);
	}

	@Override
	public void update() {
		super.update();
		StepResult stepResult = brain.activate();
		applyStepResult(stepResult);
	}

	private void applyStepResult(StepResult stepResult) {
		switch (stepResult.getMoveDirection()) {
		case _UNDEFINED:
			// ignore !
			break;
		case LEFT:
			this.moveLeft();
			break;
		case RIGHT:
			this.moveRight();
			break;
		default:
			throw new IllegalArgumentException("wrong move direction in result step");
		}

		if (stepResult.isFire()) {
			this.fire();
		}
	}
}
