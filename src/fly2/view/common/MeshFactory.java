package fly2.view.common;

import android.graphics.Bitmap;

import javax.microedition.khronos.opengles.GL10;

public class MeshFactory {

	private GL10 gl;
	private Mesh playerPlane;
	private GameBitmapFactory bitmapFactory;

	public MeshFactory(GL10 gl, GameBitmapFactory bitmapFactory) {
		if (gl == null)
			throw new NullPointerException("gl");

		if (bitmapFactory == null)
			throw new NullPointerException("bitmapFactory");

		this.gl = gl;
		this.bitmapFactory = bitmapFactory;
	}

	public Mesh getPlayerPlaneMesh() {
		if (playerPlane == null)
			createPlayerPlaneMesh();

		return playerPlane;
	}

	private void createPlayerPlaneMesh() {
		Bitmap bitmap = bitmapFactory.getPlayerPlaneBitmap();
		int textureId = TextureLoader.fromBitmap(gl, bitmap);
		playerPlane = new Mesh(PlayerPlaneVertexes.getVertexes(), PlayerPlaneVertexes.getVertexes(), textureId);
		bitmap.recycle();
	}
}
