package fly2.view.surface;

import static javax.microedition.khronos.opengles.GL10.GL_FLOAT;
import static javax.microedition.khronos.opengles.GL10.GL_TRIANGLES;
import static javax.microedition.khronos.opengles.GL10.GL_VERTEX_ARRAY;
import fly2.common.BufferBuilder;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CloudsBackgroundView implements BackgroundView {

//	private float width, height;
	private FloatBuffer vertexBuffer;
	private float[] vertexes;

	public CloudsBackgroundView(float width, float height) {
//		this.width = width;
//		this.height = height;
		this.vertexes = createVertexes(width, height);
		this.vertexBuffer = BufferBuilder.asFloat(vertexes);
	}

	public void draw(GL10 gl) {
		gl.glColor4f(0, 0, 0.4f, 1.0f);
		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL_TRIANGLES, 0, vertexes.length / 2);
		gl.glDisableClientState(GL_VERTEX_ARRAY);
	}

	private static float[] createVertexes(float width, float height) {
		return new float[] {
				0, 0,
				width, 0,
				0, height,

				width, 0,
				width, height,
				0, height
		};
	}
}
