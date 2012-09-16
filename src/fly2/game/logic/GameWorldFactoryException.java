package fly2.game.logic;

public class GameWorldFactoryException extends RuntimeException {

	private static final long serialVersionUID = -1599603530765864364L;

	public GameWorldFactoryException() {
		super();
	}

	public GameWorldFactoryException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public GameWorldFactoryException(String detailMessage) {
		super(detailMessage);
	}

	public GameWorldFactoryException(Throwable throwable) {
		super(throwable);
	}
}
