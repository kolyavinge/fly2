package fly2.game.logic;

public class PlayerPlaneActions implements fly2.game.frontend.PlayerPlaneActions {

	private Plane playerPlane;

	public PlayerPlaneActions(Plane playerPlane) {
		if (playerPlane == null)
			throw new NullPointerException("playerPlane");
		this.playerPlane = playerPlane;
	}

	public void moveLeft() {
		playerPlane.moveX(-1.0);
	}

	public void moveRight() {
		playerPlane.moveX(1.0);
	}

	public void fire() {
		playerPlane.fire();
	}
}
