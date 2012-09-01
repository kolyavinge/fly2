package fly2.view;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import fly2.game.frontend.GameModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import static javax.microedition.khronos.opengles.GL10.*;

public class GameRenderer implements Renderer {

	private GameModel gameModel;
	private FloatBuffer triangleBuffer;

	private float[] planePoints = {

			// нос

			0f, 0f,
			31f, 31f,
			13f, 3f,

			0f, 0f,
			47f, 64f,
			31f, 31f,

			0f, 0f,
			58f, 105f,
			47f, 64f,

			0f, 0f,
			58f, 105f,
			63f, 165f,

			0f, 0f,
			63f, 165f,
			68f, 304f,

			0f, 0f,
			68f, 304f,
			66f, 400f,

			0f, 0f,
			0f, 400f,
			66f, 400f,

			// крыло

			0f, 400f,
			66f, 400f,
			704, 445f,

			0f, 400f,
			704, 445f,
			742f, 464f,

			0f, 400f,
			742f, 464f,
			763f, 485f,

			0f, 400f,
			763f, 485f,
			769f, 524f,

			0f, 400f,
			769f, 524f,
			751f, 584f,

			0f, 400f,
			751f, 584f,
			700f, 654f,

			0f, 400f,
			700f, 654f,
			663f, 660f,

			0f, 400f,
			663f, 660f,
			122f, 767f,

			0f, 400f,
			122f, 767f,
			0f, 767f,

			// хернюшка

			0f, 767f,
			122f, 767f,
			79f, 804f,

			0f, 767f,
			79f, 804f,
			60f, 857f,

			0f, 767f,
			60f, 857f,
			41f, 1092f,

			0f, 767f,
			41f, 1092f,
			0f, 1092f,

			// маленькое крыло

			0f, 1185f,
			0f, 1092f,
			41f, 1092f,

			0f, 1185f,
			41f, 1092f,
			283f, 1170f,

			0f, 1185f,
			283f, 1170f,
			302f, 1185f,

			0f, 1185f,
			302f, 1185f,
			313f, 1224f,

			0f, 1185f,
			313f, 1224f,
			303f, 1256f,

			0f, 1185f,
			303f, 1256f,
			263f, 1283f,

			0f, 1185f,
			263f, 1283f,
			55f, 1289f,

			0f, 1185f,
			55f, 1289f,
			19f, 1235f,

			0f, 1185f,
			19f, 1235f,
			0f, 1346f,
	};

	public GameRenderer(GameModel gameModel) {
		if (gameModel == null)
			throw new NullPointerException("gameModel");

		this.gameModel = gameModel;

		for (int i = 0; i < planePoints.length - 1; i += 2) {
			planePoints[i] /= 772f;
			planePoints[i + 1] /= 1346f;
		}

		ByteBuffer bb = ByteBuffer.allocateDirect(2 * 4 * planePoints.length);
		bb.order(ByteOrder.nativeOrder());
		triangleBuffer = bb.asFloatBuffer();
		triangleBuffer.put(planePoints);

		for (int i = 0; i < planePoints.length - 1; i += 2) {
			planePoints[i] *= -1;
		}

		triangleBuffer.put(planePoints);

		triangleBuffer.position(0);
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL_FLOAT, 0, triangleBuffer);
		gl.glDrawArrays(GL_TRIANGLES, 0, planePoints.length);
		gl.glDisableClientState(GL_VERTEX_ARRAY);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 10.0f);
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0f, 0.5f, 1.5f, 0f, 0.5f, 0f, 0f, 1.0f, 0.0f);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepthf(10.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
		gl.glDisable(GL_DITHER);
	}
}
