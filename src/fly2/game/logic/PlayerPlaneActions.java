package fly2.game.logic;

public class PlayerPlaneActions implements fly2.game.frontend.PlayerPlaneActions {

	private GameWorld gameWorld;
	private Plane playerPlane;

	public PlayerPlaneActions(GameWorld gameWorld, Plane playerPlane) {
		if (gameWorld == null)
			throw new NullPointerException("gameWorld");

		if (playerPlane == null)
			throw new NullPointerException("playerPlane");

		this.gameWorld = gameWorld;
		this.playerPlane = playerPlane;
	}

	public void moveLeft() {
		playerPlane.moveX(-1.0);
		gameWorld.checkOutOfWorld(playerPlane);
	}

	public void moveRight() {
		playerPlane.moveX(1.0);
		gameWorld.checkOutOfWorld(playerPlane);
	}

	public void fire() {
		playerPlane.fire();
	}
}
