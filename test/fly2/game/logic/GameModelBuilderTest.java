package fly2.game.logic;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;
import fly2.app.AssetFileReader;
import fly2.game.enemy.NormalEnemyBrainContainer;
import fly2.game.frontend.Difficulty;

public class GameModelBuilderTest extends InstrumentationTestCase {

	private GameModelBuilder builder;
	private AssetFileReader fileReader;

	public void setUp() {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		fileReader = new AssetFileReader(assetManager);
		builder = new GameModelBuilder();
	}

	public void testBuildDefault() {
		builder.setFileReader(fileReader);
		GameModel gameModel = (GameModel) builder.buildModel();
		assertNotNull(gameModel.brainController);
		assertTrue(gameModel.brainController.getBrainContainer() instanceof NormalEnemyBrainContainer);
	}
	
	public void testBuildNormal() {
		builder.setDifficulty(Difficulty.NORMAL);
		builder.setFileReader(fileReader);
		GameModel gameModel = (GameModel) builder.buildModel();
		assertNotNull(gameModel.brainController);
		assertTrue(gameModel.brainController.getBrainContainer() instanceof NormalEnemyBrainContainer);
	}
}
