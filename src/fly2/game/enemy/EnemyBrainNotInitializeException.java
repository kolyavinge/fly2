package fly2.game.enemy;

public class EnemyBrainNotInitializeException extends RuntimeException {

	private static final long serialVersionUID = -4828633865662042199L;

	public EnemyBrainNotInitializeException() {
		super();
	}

	public EnemyBrainNotInitializeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public EnemyBrainNotInitializeException(String detailMessage) {
		super(detailMessage);
	}

	public EnemyBrainNotInitializeException(Throwable throwable) {
		super(throwable);
	}
}
