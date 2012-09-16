package fly2.game.common;

import junit.framework.TestCase;

public class GameWorldFileParserTest extends TestCase {

	private GameWorldFileParser parser;

	public void testSetNullHandler() {
		try {
			parser = getParser();
			parser.setHandler(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testParseWithoutHandler() {
		try {
			parser = getParser();
			parser.parse();
			fail();
		} catch (GameWorldFileParserException exp) {
		}
	}

	private GameWorldFileParser getParser() {

		return new GameWorldFileParser() {
			@Override
			protected void tryParse(GameWorldFileParserHandler handler) throws Exception {
				// empty !
			}
		};
	}
}
