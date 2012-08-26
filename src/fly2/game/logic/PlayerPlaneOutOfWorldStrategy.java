package fly2.game.logic;

import fly2.phyzix.ext.ReturnedOutOfWorldStrategy;

/**
 * Поведение игрока, при вылете за пределы игрового мира.
 * Если игрок вылетел за края - он возвращается обрато в мир.
 * Если он долетел до верхнего края - вызывается метод execute() объекта PlayerPlaneFinishLevelCallback.
 */
public class PlayerPlaneOutOfWorldStrategy extends ReturnedOutOfWorldStrategy<Plane> {

	private CompleteLevelCallback callback;

	public PlayerPlaneOutOfWorldStrategy(CompleteLevelCallback callback) {
		if (callback == null)
			throw new NullPointerException("callback");

		this.callback = callback;
	}

	@Override
	public void activate(Plane plane, double worldWidth, double worldHeight) {
		if (plane.getY() >= worldHeight) {
			callback.execute();
		} else {
			super.activate(plane, worldWidth, worldHeight);
		}
	}
}
