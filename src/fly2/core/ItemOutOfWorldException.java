package fly2.core;

public class ItemOutOfWorldException extends RuntimeException {

	private static final String message = "Объект находится вне границ игрового мира";
	private WorldItem item;

	public ItemOutOfWorldException(WorldItem item) {
		super(message);
		this.item = item;
	}

	public WorldItem getWorldItem() {
		return item;
	}
}
