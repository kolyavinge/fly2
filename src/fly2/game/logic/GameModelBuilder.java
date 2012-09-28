package fly2.game.logic;

import fly2.game.common.ResourceFileReader;
import fly2.game.enemy.EnemyBrainContainer;
import fly2.game.enemy.EnemyBrainController;
import fly2.game.enemy.NormalEnemyBrainContainer;
import fly2.game.frontend.Difficulty;

public final class GameModelBuilder {

	private ResourceFileReader fileReader;
	private Difficulty difficulty = Difficulty.NORMAL;

	public void setFileReader(ResourceFileReader fileReader) {
		this.fileReader = fileReader;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public fly2.game.frontend.GameModel buildModel() {
		GameWorldFactory gameWorldFactory = new FileSystemGameWorldFactory(fileReader);

		EnemyBrainContainer brainContainer = getBrainContainer();
		EnemyBrainController brainController = new EnemyBrainController();
		brainController.setBrainContainer(brainContainer);

		return new GameModel(gameWorldFactory, brainController);
	}

	private EnemyBrainContainer getBrainContainer() {
		switch (difficulty) {
		case NORMAL:
			return new NormalEnemyBrainContainer();
		default:
			throw new IllegalArgumentException();
		}
	}
}
