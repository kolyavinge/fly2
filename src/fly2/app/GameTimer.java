package fly2.app;

import fly2.game.frontend.GameModel;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

	private GameModel gameModel;
	private Timer timer;

	public GameTimer(GameModel gameModel) {
		if (gameModel == null)
			throw new NullPointerException("gameModel");
		
		this.gameModel = gameModel;
	}

	public void run() {
		timer = new Timer(true);
		timer.schedule(updateTask, 0, 20);
	}

	private final TimerTask updateTask = new TimerTask() {

		@Override
		public void run() {
			gameModel.update();
		}
	};
}
