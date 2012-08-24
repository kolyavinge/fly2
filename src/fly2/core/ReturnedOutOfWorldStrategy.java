package fly2.core;

import fly2.common.*;

/**
 * Возвращает объект в игровой мир, когда он из него вылетает
 */
public class ReturnedOutOfWorldStrategy<T extends WorldItem> implements OutOfWorldStrategy<T> {

	public void activate(WorldItem item, double worldWidth, double worldHeight) {
		Bounds bounds = item.getBounds();

		if (bounds.getLeftUpX() < 0) {
			item.setLeftUpX(0);
		} else if (bounds.getRightUpX() >= worldWidth) {
			item.setLeftUpX(worldWidth - item.getWidth());
		}

		if (bounds.getLeftUpY() < 0) {
			item.setLeftUpY(item.getHeight());
		} else if (bounds.getRightUpY() >= worldHeight) {
			item.setLeftUpY(worldHeight - item.getHeight());
		}
	}
}
