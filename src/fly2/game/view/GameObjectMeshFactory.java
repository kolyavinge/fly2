package fly2.game.view;

import fly2.view.Mesh2d;
import fly2.view.TextureLoader;
import android.graphics.Bitmap;

import javax.microedition.khronos.opengles.GL10;

public class GameObjectMeshFactory {

	private GL10 gl;
	private GameBitmapFactory bitmapFactory;
	private Mesh2d playerPlane;

	public GameObjectMeshFactory(GL10 gl, GameBitmapFactory bitmapFactory) {
		this.gl = gl;
		this.bitmapFactory = bitmapFactory;
	}

	public Mesh2d getPlayerPlaneMesh() {
		if (playerPlane == null)
			createPlayerPlaneMesh();

		return playerPlane;
	}

	private void createPlayerPlaneMesh() {
		Bitmap bitmap = bitmapFactory.getPlayerPlaneBitmap();
		int textureId = TextureLoader.fromBitmap(gl, bitmap);
		playerPlane = new Mesh2d(PlayerPlaneVertexes.getVertexes2d(), PlayerPlaneVertexes.getVertexes2d(), textureId);
		bitmap.recycle();
	}
}
