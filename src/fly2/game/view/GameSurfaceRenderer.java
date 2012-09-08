package fly2.game.view;

import static javax.microedition.khronos.opengles.GL10.*;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import fly2.game.frontend.GameModel;
import fly2.game.frontend.Plane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameSurfaceRenderer implements GLSurfaceView.Renderer {

	private GameModel gameModel;
	private BackgroundView background;
	private Collection<GameObjectView> viewCollection;
	private GameBitmapFactory bitmapFactory;
	private PlayerPlaneView playerPlaneView;
	private GameObjectViewFactory viewFactory;
	private Camera camera;

	public GameSurfaceRenderer(GameModel gameModel, GameBitmapFactory bitmapFactory) {
		this.gameModel = gameModel;
		this.bitmapFactory = bitmapFactory;
		this.viewCollection = new ArrayList<GameObjectView>();
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		gl.glPushMatrix();

		float shiftX = (float) gameModel.getWorld().getWidth() / 2f;
		gl.glTranslatef(-shiftX, 0f, 0f);

		camera.look();
		GLU.gluLookAt(gl,
				(float) camera.getLookX(), (float) camera.getLookY(), 13f,
				(float) camera.getLookX(), (float) camera.getLookY(), 0f,
				0f, 1f, 0f);

		background.draw(gl);
		drawViewsAndRemoveDestroyed(gl);

		gl.glPopMatrix();
	}

	private void drawViewsAndRemoveDestroyed(GL10 gl) {
		Iterator<GameObjectView> iter = viewCollection.iterator();
		while (iter.hasNext()) {
			GameObjectView view = iter.next();
			if (view.isDestroyed() == false) {
				view.draw(gl);
			} else {
				iter.remove();
			}
		}
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		initGL(gl);
		createBackground(gl);
		createViewFactory(gl);
		createGameObjectViews();
		createCamera();
	}

	private void initGL(GL10 gl) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
		gl.glDisable(GL_DITHER);
		gl.glFrontFace(GL_CCW);
		gl.glEnable(GL_CULL_FACE);
		gl.glCullFace(GL_BACK);
	}

	private void createGameObjectViews() {
		Plane plane = gameModel.getPlayerPlane();
		playerPlaneView = viewFactory.getPlayerPlaneView(plane);
		viewCollection.add(playerPlaneView);
	}

	private void createViewFactory(GL10 gl) {
		GameObjectMeshFactory meshFactory = new GameObjectMeshFactory(gl, bitmapFactory);
		viewFactory = new GameObjectViewFactory(meshFactory);
	}

	private void createCamera() {
		camera = new Camera();
		camera.setCameraWidth(10.0);
		camera.setWorldWidth(gameModel.getWorld().getWidth());
		camera.setObservableObject(playerPlaneView);
	}

	private void createBackground(GL10 gl) {
		float width = (float) gameModel.getWorld().getWidth();
		float height = (float) gameModel.getWorld().getHeight();

		background = new FlatBackgroundView(
				gl,
				bitmapFactory.getBackgroundBitmap(),
				width,
				height);
	}
}
