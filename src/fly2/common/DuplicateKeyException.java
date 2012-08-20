package fly2.common;

public class DuplicateKeyException extends RuntimeException {

	private static final long serialVersionUID = 1664642697244192412L;

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
