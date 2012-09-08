package fly2.game.view;

import android.graphics.Bitmap;
import fly2.view.Mesh2d;
import fly2.view.TextureLoader;

import javax.microedition.khronos.opengles.GL10;

public class FlatBackgroundView implements BackgroundView {

	private Mesh2d mesh;

	public FlatBackgroundView(GL10 gl, Bitmap backBitmap, float width, float height) {
		float[] vertexes = getVertexes(width, height);
		float[] textureVertexes = getTextureVertexes();
		int textureId = TextureLoader.fromBitmap(gl, backBitmap);
		mesh = new Mesh2d(vertexes, textureVertexes, textureId);
	}

	public void draw(GL10 gl) {
		mesh.draw(gl);
	}

	private static float[] getVertexes(float width, float height) {

		return new float[] {
				0, 0,
				width, 0,
				0, height,

				width, 0,
				width, height,
				0, height
		};
	}

	private static float[] getTextureVertexes() {

		return new float[] {
				0, 1f,
				1f, 1f,
				0, 0,

				1f, 1f,
				1f, 0,
				0, 0,
		};
	}
}
