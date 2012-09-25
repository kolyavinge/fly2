package fly2.game.common;

import junit.framework.TestCase;

public class PlaneIdGeneratorTest extends TestCase {

	private PlaneIdGenerator generator;

	public void setUp() {
		generator = PlaneIdGenerator.getInstance();
	}

	public void testGenerate() {
		for (int i = 1; i <= 1000; i++) {
			assertEquals(i, generator.generate());
		}
	}
}
