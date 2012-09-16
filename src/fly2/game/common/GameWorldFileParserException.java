package fly2.game.common;

public class GameWorldFileParserException extends RuntimeException {

	private static final long serialVersionUID = -1547001387051389747L;

	public GameWorldFileParserException() {
		super();
	}

	public GameWorldFileParserException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public GameWorldFileParserException(String detailMessage) {
		super(detailMessage);
	}

	public GameWorldFileParserException(Throwable throwable) {
		super(throwable);
	}
}
