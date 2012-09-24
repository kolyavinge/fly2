package fly2.game.enemy;

import fly2.game.frontend.Direction;
import static fly2.game.frontend.Direction.*;

public final class StepResult {

	private boolean fireFlag;
	private Direction moveDirection;

	public StepResult() {
		fireFlag = false;
		moveDirection = _UNDEFINED;
	}
	
	public boolean isFire() {
		return fireFlag;
	}
	
	public Direction getMoveDirection() {
		return moveDirection;
	}

	public void fire() {
		fireFlag = true;
	}

	public void moveLeft() {
		moveDirection = LEFT;
	}

	public void moveRight() {
		moveDirection = RIGHT;
	}
}
