package fly2.game.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import fly2.game.frontend.GameModel;

public class GameSurfaceView extends GLSurfaceView {

	private GameModel gameModel;
	private GameSurfaceRenderer renderer;
	private GameBitmapFactory bitmapFactory;

	public GameSurfaceView(GameModel gameModel, Context context) {
		super(context);
		this.gameModel = gameModel;
		bitmapFactory = new LightBitmapFactory(getResources());
		renderer = new GameSurfaceRenderer(gameModel, bitmapFactory);
		setRenderer(renderer);
		requestFocus();
		setFocusableInTouchMode(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			gameModel.getPlayerPlaneActions().moveLeft();
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			gameModel.getPlayerPlaneActions().moveRight();
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			gameModel.getPlayerPlaneActions().fire();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}
}
