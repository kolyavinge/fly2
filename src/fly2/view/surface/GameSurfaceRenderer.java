package fly2.view.surface;

import static javax.microedition.khronos.opengles.GL10.*;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import fly2.game.frontend.GameModel;
import fly2.game.frontend.Plane;
import fly2.view.common.GameBitmapFactory;
import fly2.view.common.MeshFactory;

import java.util.ArrayList;
import java.util.Collection;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameSurfaceRenderer implements GLSurfaceView.Renderer {

	private GameModel gameModel;
	private Collection<View> viewCollection;
	private GameBitmapFactory bitmapFactory;
	private ViewFactory viewFactory;

	public GameSurfaceRenderer(GameModel gameModel, GameBitmapFactory bitmapFactory) {
		if (gameModel == null)
			throw new NullPointerException("gameModel");

		this.gameModel = gameModel;
		this.bitmapFactory = bitmapFactory;
		this.viewCollection = new ArrayList<View>();
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		for (View view : viewCollection) {
			view.draw(gl);
		}
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
		initGLOptions(gl);
		createViewFactory(gl);
		Plane plane = gameModel.getPlayerPlane();
		PlayerPlaneView playerPlaneView = viewFactory.getPlayerPlaneView(plane);
		this.viewCollection.add(playerPlaneView);
	}

	private void createViewFactory(GL10 gl) {
		MeshFactory meshFactory = new MeshFactory(gl, bitmapFactory);
		viewFactory = new ViewFactory(meshFactory);
	}

	private void initGLOptions(GL10 gl) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepthf(10.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
		gl.glDisable(GL_DITHER);
		gl.glFrontFace(GL_CCW);
		gl.glEnable(GL_CULL_FACE);
		gl.glCullFace(GL_BACK);
		//		gl.glEnable(GL_TEXTURE_2D);
	}
}
