package fly2.game.common;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;
import fly2.app.AssetFileReader;
import fly2.common.Tuple;
import fly2.unittest.TestGameWorldFileParserHandler;
import fly2.unittest.TestPlaneFactory;

import java.io.IOException;
import java.util.Iterator;

public class JsonGameWorldFileParserTest extends InstrumentationTestCase {

	private JsonGameWorldFileParser parser;
	private TestGameWorldFileParserHandler handler;
	private Iterator<Tuple<Double, Double>> enemyIterator;

	public void setUp() throws IOException {
		ResourceFileReader reader = getFileReader();
		parser = new JsonGameWorldFileParser(reader.getFileText("testWorld"));
		handler = new TestGameWorldFileParserHandler();
		parser.setHandler(handler);
	}

	public void testParse() {
		parse();
		assertGameWorld(20, 100);
		assertPlaneFactory(TestPlaneFactory.class);
		assertEnemiesCount(3);
		assertEnemy(1, 30);
		assertEnemy(20, 50);
		assertEnemy(35, 70);
	}

	public void testNullJson() {
		try {
			new JsonGameWorldFileParser(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testEmptyJson() {
		try {
			parser = new JsonGameWorldFileParser("");
			handler = new TestGameWorldFileParserHandler();
			parser.setHandler(handler);
			parser.parse();
			fail();
		} catch (GameWorldFileParserException exp) {
		}
	}

	public void testNotJson() {
		try {
			parser = new JsonGameWorldFileParser("this is not a json");
			handler = new TestGameWorldFileParserHandler();
			parser.setHandler(handler);
			parser.parse();
			fail();
		} catch (GameWorldFileParserException exp) {
		}
	}

	private void parse() {
		parser.parse();
		enemyIterator = handler.getEnemies().iterator();
	}

	private void assertGameWorld(double width, double height) {
		assertEquals(width, handler.getGameWorldWidth(), 0.001);
		assertEquals(height, handler.getGameWorldHeight(), 0.001);
	}

	private void assertPlaneFactory(Class planeFactoryClass) {
		assertEquals(planeFactoryClass.getName(), handler.getPlaneFactory().getClass().getName());
	}

	private void assertEnemiesCount(int count) {
		assertEquals(count, handler.getEnemies().size());
	}

	private void assertEnemy(double x, double y) {
		Tuple<Double, Double> enemy = enemyIterator.next();
		assertEquals(x, enemy.getFirst(), 0.001);
		assertEquals(y, enemy.getSecond(), 0.001);
	}

	private ResourceFileReader getFileReader() {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		return new AssetFileReader(assetManager);
	}
}
