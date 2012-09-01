package fly2.view.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import fly2.game.frontend.GameModel;
import fly2.view.common.GameBitmapFactory;
import fly2.view.common.LightBitmapFactory;

public class GameSurfaceView extends GLSurfaceView {

	public GameSurfaceView(GameModel gameModel, Context context, AttributeSet attrs) {
		super(context, attrs);
		setRenderer(createRenderer(gameModel));
	}

	public GameSurfaceView(GameModel gameModel, Context context) {
		super(context);
		setRenderer(createRenderer(gameModel));
	}

	private GLSurfaceView.Renderer createRenderer(GameModel gameModel) {
		GameBitmapFactory bitmapFactory = new LightBitmapFactory(getResources());
		return new GameSurfaceRenderer(gameModel, bitmapFactory);
	}
}
