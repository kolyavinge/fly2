package fly2.app;

import android.app.Activity;
import android.os.Bundle;
import fly2.game.common.ResourceFileReader;
import fly2.game.frontend.Difficulty;
import fly2.game.frontend.GameModel;
import fly2.game.logic.GameModelBuilder;
import fly2.game.view.GameSurfaceView;

public class Fly2Activity extends Activity {

	private GameTimer gameTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameModel gameModel = createGameModel();
		createGameSurface(gameModel);
		createAndRunGameTimer(gameModel);
	}

	private void createAndRunGameTimer(GameModel gameModel) {
		gameTimer = new GameTimer(gameModel);
		gameTimer.run();
	}

	private GameSurfaceView createGameSurface(GameModel gameModel) {
		GameSurfaceView gameView = new GameSurfaceView(gameModel, this);
		setContentView(gameView);

		return gameView;
	}

	private GameModel createGameModel() {
		ResourceFileReader fileReader = getFileReader();
		GameModelBuilder builder = new GameModelBuilder();
		builder.setFileReader(fileReader);
		builder.setDifficulty(Difficulty.NORMAL);

		return builder.buildModel();
	}

	private ResourceFileReader getFileReader() {
		return new AssetFileReader(getAssets());
	}
}
