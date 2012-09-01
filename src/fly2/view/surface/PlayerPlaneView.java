package fly2.view.surface;

import fly2.game.frontend.Plane;
import fly2.view.common.Mesh;

import javax.microedition.khronos.opengles.GL10;

public class PlayerPlaneView implements View {

	private Plane plane;
	private Mesh mesh;

	public PlayerPlaneView(Plane plane, Mesh mesh) {
		this.plane = plane;
		this.mesh = mesh;
	}

	public void draw(GL10 gl) {
		mesh.draw(gl);
	}
}
