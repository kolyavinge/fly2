package fly2.view.common;

import static javax.microedition.khronos.opengles.GL10.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Mesh {
	
	private static final int FLOAT_SIZE = 4;

	private float[] vertexes;
	private float[] textureVertexes;
	private int textureId;
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	
	public Mesh(float[] vertexes, float[] textureVertexes, int textureId) {
		this.vertexes = vertexes;
		this.textureVertexes = textureVertexes;
		this.textureId = textureId;
		createVertexBuffer();
		createTextureBuffer();
	}
	
	private void createTextureBuffer() {
		ByteBuffer tbb = ByteBuffer.allocateDirect(FLOAT_SIZE * vertexes.length);
		tbb.order(ByteOrder.nativeOrder());
		textureBuffer = tbb.asFloatBuffer();
		textureBuffer.put(vertexes);
		textureBuffer.position(0);
	}

	private void createVertexBuffer() {
		ByteBuffer bb = ByteBuffer.allocateDirect(FLOAT_SIZE * vertexes.length);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(textureVertexes);
		vertexBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glEnable(GL_TEXTURE_2D);
		
		gl.glBindTexture(GL_TEXTURE_2D, textureId);
		
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL_FLOAT, 0, vertexBuffer);

		gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL_FLOAT, 0, textureBuffer);

		gl.glDrawArrays(GL_TRIANGLES, 0, vertexes.length);

		gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL_VERTEX_ARRAY);

		gl.glDisable(GL_TEXTURE_2D);
	}
}
