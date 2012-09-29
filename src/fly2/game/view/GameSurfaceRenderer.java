package fly2.game.view;

import static javax.microedition.khronos.opengles.GL10.*;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import fly2.game.frontend.GameModel;
import fly2.game.frontend.Plane;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameSurfaceRenderer implements GLSurfaceView.Renderer {

	private GameModel gameModel;
	private BackgroundView background;
	private Collection<GameObjectView> viewCollection;
	private GameBitmapFactory bitmapFactory;
	private PlaneView playerPlaneView;
	private GameObjectViewFactory viewFactory;

	public GameSurfaceRenderer(GameModel gameModel, GameBitmapFactory bitmapFactory) {
		this.gameModel = gameModel;
		this.bitmapFactory = bitmapFactory;
		this.viewCollection = new java.util.concurrent.LinkedBlockingQueue<GameObjectView>();
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glPushMatrix();
		cameraShift(gl);
		background.draw(gl);
		drawObjectsAndRemoveDestroyed(gl);
		gl.glPopMatrix();
	}

	private void cameraShift(GL10 gl) {
		gl.glTranslatef(0f, -(float) gameModel.getPlayerPlane().getY(), 0f);
	}

	private void drawObjectsAndRemoveDestroyed(GL10 gl) {
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

	public void onSurfaceChanged(GL10 gl, int screenWidth, int screenHeight) {
		gl.glViewport(0, 0, screenWidth, screenHeight);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		float w = (float) gameModel.getWorld().getWidth();
		float h = w * screenHeight / screenWidth;
		GLU.gluOrtho2D(gl, 0, w, 0, h);
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		initGL(gl);
		createBackground(gl);
		createViewFactory(gl);
		createGameObjectViews();
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
		createPlayerPlaneView();
		createEnemyPlaneViews();
	}

	private void createPlayerPlaneView() {
		Plane plane = gameModel.getPlayerPlane();
		playerPlaneView = viewFactory.getPlayerPlaneView(plane);
		viewCollection.add(playerPlaneView);
	}
	
	private void createEnemyPlaneViews() {
		for (Plane enemy : gameModel.getEnemyPlanes()) {
			PlaneView enemyPlaneView = viewFactory.getEnemyPlaneView(enemy);
			viewCollection.add(enemyPlaneView);
		}
	}

	private void createViewFactory(GL10 gl) {
		GameObjectMeshFactory meshFactory = new GameObjectMeshFactory(gl, bitmapFactory);
		viewFactory = new GameObjectViewFactory(meshFactory);
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
