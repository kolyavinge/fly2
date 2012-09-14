package fly2.game.enemy;

import fly2.common.Direction;
import static fly2.common.Direction.*;

public class StepResult {

	private boolean fireFlag;
	private Direction moveDirection;

	public StepResult() {
		init();
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

	public void reset() {
		init();
	}

	private void init() {
		fireFlag = false;
		moveDirection = _UNDEFINED;
	}
}
