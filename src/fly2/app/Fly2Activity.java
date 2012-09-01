package fly2.app;

import android.app.Activity;
import android.os.Bundle;
import fly2.game.frontend.GameModel;
import fly2.game.logic.GameModelFactory;
import fly2.view.surface.GameSurfaceView;

public class Fly2Activity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameModel gameModel = GameModelFactory.getInstance().createModel();
		GameSurfaceView gameSurfaceView = new GameSurfaceView(gameModel, this);
		setContentView(gameSurfaceView);
	}
}
