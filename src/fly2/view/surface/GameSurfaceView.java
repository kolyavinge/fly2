package fly2.view.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import fly2.game.frontend.GameModel;
import fly2.view.common.GameBitmapFactory;
import fly2.view.common.LightBitmapFactory;

public class GameSurfaceView extends GLSurfaceView {

	private GameModel gameModel;
	private GameSurfaceRenderer renderer;
	private GameBitmapFactory bitmapFactory;

	public GameSurfaceView(GameModel gameModel, Context context) {
		super(context);
		this.gameModel = gameModel;
		bitmapFactory = new LightBitmapFactory(getResources());
		renderer = new GameSurfaceRenderer(gameModel, bitmapFactory, getBackgroundView());
		setRenderer(renderer);
		requestFocus();
		setFocusableInTouchMode(true);
	}

	private BackgroundView getBackgroundView() {
		float width = (float) gameModel.getWorld().getWidth();
		float height = (float) gameModel.getWorld().getHeight();

		return new CloudsBackgroundView(width, height);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			gameModel.getPlayerPlaneActions().moveLeft();
			requestRender();
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			gameModel.getPlayerPlaneActions().moveRight();
			requestRender();
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			gameModel.getPlayerPlaneActions().fire();
			requestRender();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}
}
