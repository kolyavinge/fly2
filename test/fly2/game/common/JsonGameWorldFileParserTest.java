package fly2.game.common;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;
import fly2.common.Tuple;
import fly2.common.android.ResourceFileReader;
import fly2.unittest.TestGameWorldFileParserHandler;

import java.io.IOException;
import java.util.Iterator;

public class JsonGameWorldFileParserTest extends InstrumentationTestCase {

	private JsonGameWorldFileParser parser;
	private TestGameWorldFileParserHandler handler;
	private Iterator<Tuple<Double, Double>> enemyIterator;

	public void setUp() throws IOException {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		ResourceFileReader reader = new ResourceFileReader(assetManager);
		parser = new JsonGameWorldFileParser(reader.getFileText("testWorld"));
		handler = new TestGameWorldFileParserHandler();
		parser.setHandler(handler);
	}

	public void testParse() {
		parse();
		assertGameWorld(20, 100);
		assertEnemiesCount(3);
		assertEnemy(1, 30);
		assertEnemy(20, 50);
		assertEnemy(35, 70);
	}

	public void testNullFileContent() {
		try {
			new JsonGameWorldFileParser(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testEmptyFileContent() {
		try {
			parser = new JsonGameWorldFileParser("");
			handler = new TestGameWorldFileParserHandler();
			parser.setHandler(handler);
			parser.parse();
			fail();
		} catch (GameWorldFileParserException exp) {
		}
	}
	
	public void testNotJsonFileContent() {
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

	private void assertEnemiesCount(int count) {
		assertEquals(count, handler.getEnemies().size());
	}

	private void assertEnemy(double x, double y) {
		Tuple<Double, Double> enemy = enemyIterator.next();
		assertEquals(x, enemy.getFirst(), 0.001);
		assertEquals(y, enemy.getSecond(), 0.001);
	}
}
