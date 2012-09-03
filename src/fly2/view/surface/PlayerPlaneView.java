package fly2.view.surface;

import fly2.game.frontend.Plane;
import fly2.view.common.Mesh;
import javax.microedition.khronos.opengles.GL10;

public class PlayerPlaneView implements View<Plane> {

	private Plane plane;
	private Mesh mesh;

	public PlayerPlaneView(Plane plane, Mesh mesh) {
		this.plane = plane;
		this.mesh = mesh;
	}

	public void draw(GL10 gl) {
		gl.glPushMatrix();
		double x = plane.getX() + plane.getWidth();
		double y = plane.getY() + plane.getHeight();
		gl.glTranslatef((float) x, (float) y, 0f);
		gl.glRotatef(180.0f, 0f, 0f, 1f);
		mesh.draw(gl);
		gl.glPopMatrix();
	}

	public Plane getGameModelObject() {
		return plane;
	}

	public boolean isDestroyed() {
		return plane.isDestroyed();
	}
}
