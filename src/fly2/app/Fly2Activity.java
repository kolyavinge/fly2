package fly2.app;

import android.app.Activity;
import android.os.Bundle;
import fly2.game.frontend.GameModel;
import fly2.game.logic.GameModelFactory;
import fly2.game.view.GameSurfaceView;

public class Fly2Activity extends Activity {
	
	private GameTimer gameTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameModel gameModel = GameModelFactory.getInstance().createModel();
		GameSurfaceView gameView = new GameSurfaceView(gameModel, this);
		setContentView(gameView);
		gameTimer = new GameTimer(gameModel);
		gameTimer.run();
	}
}
