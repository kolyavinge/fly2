package fly2.game.logic;

import fly2.unittest.framework.GameModelTestCase;

public class GameModelTest extends GameModelTestCase {

	public void testUpdate() {
		createWorld();

		updateGameModel();

		assertPlayerFly();
		assertEnemyFly();
		assertEnemyBrainActivated();
	}
	
	public void testNew() {
		
	}

	public void testNullGameWorldFactory() {
		try {
			new GameModel(null, getEnemyBrainController());
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testNullEnemyBrainController() {
		try {
			new GameModel(getGameWorldFactory(), null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
