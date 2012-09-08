package fly2.app;

import android.view.View;
import fly2.game.frontend.GameModel;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

	private GameModel gameModel;
	private View gameView;
	private Timer timer;

	public GameTimer(GameModel gameModel, View gameView) {
		if (gameModel == null)
			throw new NullPointerException("gameModel");
		
		if (gameView == null)
			throw new NullPointerException("gameView");
		
		this.gameModel = gameModel;
		this.gameView = gameView;
	}

	public void run() {
		timer = new Timer(true);
		timer.schedule(updateTask, 0, 25);
	}

	private final TimerTask updateTask = new TimerTask() {

		@Override
		public void run() {
			gameModel.update();
			gameView.postInvalidate();
		}
	};
}
