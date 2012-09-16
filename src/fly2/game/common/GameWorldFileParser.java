package fly2.game.common;

public abstract class GameWorldFileParser {

	private GameWorldFileParserHandler handler;

	public final void setHandler(GameWorldFileParserHandler handler) {
		if (handler == null)
			throw new NullPointerException("handler");

		this.handler = handler;
	}

	public final void parse() {
		if (handler == null)
			throw new GameWorldFileParserException("Handler was not initialize");
		try {
			tryParse(handler);
		} catch (Exception exp) {
			throw new GameWorldFileParserException(exp);
		}
	}

	protected abstract void tryParse(GameWorldFileParserHandler handler) throws Exception;
}
