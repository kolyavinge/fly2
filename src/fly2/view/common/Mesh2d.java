package fly2.view.common;

import static javax.microedition.khronos.opengles.GL10.*;
import fly2.common.BufferBuilder;

import java.nio.Buffer;

import javax.microedition.khronos.opengles.GL10;

public class Mesh2d {

	private float[] vertexes;
	private int textureId;
	private Buffer vertexBuffer;
	private Buffer textureBuffer;

	public Mesh2d(float[] vertexes, float[] textureVertexes, int textureId) {
		this.vertexes = vertexes;
		this.textureId = textureId;
		vertexBuffer = BufferBuilder.asFloat(vertexes);
		textureBuffer = BufferBuilder.asFloat(textureVertexes);
	}

	public void draw(GL10 gl) {
		gl.glColor4f(1f, 1f, 1f, 1f);

		gl.glEnable(GL_TEXTURE_2D);

		gl.glBindTexture(GL_TEXTURE_2D, textureId);

		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL_FLOAT, 0, vertexBuffer);

		gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL_FLOAT, 0, textureBuffer);

		gl.glDrawArrays(GL_TRIANGLES, 0, vertexes.length / 2);

		gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL_VERTEX_ARRAY);

		gl.glDisable(GL_TEXTURE_2D);
	}
}
