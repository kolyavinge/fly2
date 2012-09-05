package fly2.view.surface;

import fly2.game.frontend.Plane;
import fly2.view.common.Mesh2d;
import fly2.view.common.MeshFactory;

public class ViewFactory {

	private MeshFactory meshFactory;

	public ViewFactory(MeshFactory meshFactory) {
		if (meshFactory == null)
			throw new NullPointerException("meshFactory");

		this.meshFactory = meshFactory;
	}

	public PlayerPlaneView getPlayerPlaneView(Plane plane) {
		Mesh2d mesh = meshFactory.getPlayerPlaneMesh();
		return new PlayerPlaneView(plane, mesh);
	}
}
