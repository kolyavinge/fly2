package fly2.app;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import fly2.game.frontend.GameModel;
import fly2.game.logic.GameModelFactory;
import fly2.view.GameRenderer;

public class Fly2Activity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLSurfaceView view = new GLSurfaceView(this);
		GameModel gameModel = GameModelFactory.getInstance().createModel();
		GameRenderer gameRenderer = new GameRenderer(gameModel);
		view.setRenderer(gameRenderer);
		setContentView(view);
	}
}
