package fly2.game.logic;

public class PlaneActions implements fly2.game.frontend.PlaneActions {

	private Plane plane;

	public PlaneActions(Plane plane) {
		if (plane == null)
			throw new NullPointerException("plane");

		this.plane = plane;
	}

	public void moveLeft() {
		plane.moveLeft();
	}

	public void moveRight() {
		plane.moveRight();
	}

	public void fire() {
		plane.fire();
	}
}
