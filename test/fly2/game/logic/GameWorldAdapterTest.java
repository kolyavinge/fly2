package fly2.game.logic;

import fly2.game.logic.GameWorldAdapter;
import fly2.phyzix.ImpactChecker;
import fly2.phyzix.World;
import junit.framework.TestCase;

public class GameWorldAdapterTest extends TestCase {

	private GameWorldAdapter worldAdapter;
	private World world;

	public void setUp() {
		world = new World(1.0, 2.0, new ImpactChecker());
		worldAdapter = new GameWorldAdapter(world);
	}

	public void testNullWorld() {
		try {
			new GameWorldAdapter(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testGetWidth() {
		assertEquals(1.0, worldAdapter.getWidth(), 0.001);
	}

	public void testGetHeight() {
		assertEquals(2.0, worldAdapter.getHeight(), 0.001);
	}
}
