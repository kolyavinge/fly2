package fly2.core;

/**
 * Возвращает объект в игровой мир, когда он из него выпадает
 */
public class ReturnedOutOfWorldStrategy<T extends WorldItem> implements OutOfWorldStrategy<T> {

	public void activate(WorldItem item, double worldWidth, double worldHeight) {
		if (item.getX() < 0) {
			item.setX(0);
		} else if (item.getX() + item.getWidth() >= worldWidth) {
			item.setX(worldWidth - item.getWidth());
		}

		if (item.getY() < 0) {
			item.setY(0);
		} else if (item.getY() + item.getHeight() >= worldHeight) {
			item.setY(worldHeight - item.getHeight());
		}
	}
}
