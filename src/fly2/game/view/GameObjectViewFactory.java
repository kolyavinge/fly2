package fly2.game.view;

import fly2.game.frontend.Plane;
import fly2.view.Mesh2d;

public class GameObjectViewFactory {

	private GameObjectMeshFactory meshFactory;

	public GameObjectViewFactory(GameObjectMeshFactory meshFactory) {
		if (meshFactory == null)
			throw new NullPointerException("meshFactory");

		this.meshFactory = meshFactory;
	}

	public PlaneView getPlayerPlaneView(Plane plane) {
		Mesh2d mesh = meshFactory.getPlayerPlaneMesh();
		return new PlaneView(plane, mesh);
	}
	
	public PlaneView getEnemyPlaneView(Plane plane) {
		Mesh2d mesh = meshFactory.getPlayerPlaneMesh();
		return new PlaneView(plane, mesh);
	}
}
