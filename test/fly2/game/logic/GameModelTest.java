package fly2.game.logic;

import fly2.unittest.stub.TestResourceFileReader;
import junit.framework.TestCase;

public class GameModelTest extends TestCase {

	private GameModel gameModel;
	private TestResourceFileReader fileReader;

	public void setUp() {
		fileReader = new TestResourceFileReader();
	}

//	public void testNew() {
//		final String fileContent = "{" +
//				"'world': {" +
//					"'width': 123, 'height': 789," +
//					"'planeFactory': 'fly2.unittest.stub.TestPlaneFactory'," +
//                    "'enemies': []" +
//				"}" +
//	    "}";
//		fileReader.put("world_0", fileContent);
//		gameModel = new GameModel(fileReader);
//		assertNotNull(gameModel.getWorld());
//		assertEquals(123.0, gameModel.getWorld().getWidth(), 0.001);
//		assertEquals(789.0, gameModel.getWorld().getHeight(), 0.001);
//		assertNotNull(gameModel.getPlayerPlane());
//		assertEquals(0, gameModel.getEnemyPlanesCount());
//		assertEquals(0, gameModel.getBulletsCount());
//	}
}
