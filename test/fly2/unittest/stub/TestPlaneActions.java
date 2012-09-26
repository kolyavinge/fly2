package fly2.unittest.stub;

import fly2.game.frontend.PlaneActions;

public class TestPlaneActions implements PlaneActions {

	public boolean noMoveFlag, moveLeftFlag, moveRightFlag, fireFlag;

	public TestPlaneActions() {
		noMoveFlag = true;
		moveLeftFlag = moveRightFlag = fireFlag = false;
	}

	public void moveLeft() {
		moveLeftFlag = true;
		noMoveFlag = false;
	}

	public void moveRight() {
		moveRightFlag = true;
		noMoveFlag = false;
	}

	public void fire() {
		fireFlag = true;
	}
}
