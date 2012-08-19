package fly2.common;

public class DuplicateKeyException extends RuntimeException {

	public DuplicateKeyException() {
		super();
	}

	public DuplicateKeyException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public DuplicateKeyException(String detailMessage) {
		super(detailMessage);
	}

	public DuplicateKeyException(Throwable throwable) {
		super(throwable);
	}
}
