package fly2.game.view;

import fly2.game.frontend.Plane;
import fly2.view.Mesh2d;

import javax.microedition.khronos.opengles.GL10;

public class PlayerPlaneView implements GameObjectView<Plane>, CameraObservableObject {

	private Plane plane;
	private Mesh2d mesh;

	public PlayerPlaneView(Plane plane, Mesh2d mesh) {
		this.plane = plane;
		this.mesh = mesh;
	}

	public void draw(GL10 gl) {
		gl.glPushMatrix();

		double x = plane.getX() + plane.getWidth();
		double y = plane.getY() + plane.getHeight();
		gl.glTranslatef((float) x, (float) y, 0f);

		gl.glRotatef(180.0f, 0f, 0f, 1f);

		float width = (float) plane.getWidth();
		float height = (float) plane.getHeight();
		gl.glScalef(width, height, 0f);

		mesh.draw(gl);

		gl.glPopMatrix();
	}

	public Plane getGameModelObject() {
		return plane;
	}

	public boolean isDestroyed() {
		return plane.isDestroyed();
	}

	/* CameraObservableObject */

	public double getX() {
		return plane.getX();
	}

	public double getY() {
		return plane.getY();
	}

	public double getWidth() {
		return plane.getWidth();
	}

	public double getHeight() {
		return plane.getHeight();
	}
	
	/* CameraObservableObject */
}
