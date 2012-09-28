package fly2.game.logic;

import fly2.phyzix.ReturnedOutOfWorldStrategy;

/**
 * Определяет поведение вражеского самолетика, когда он вылетает за пределы игрового мира.
 * Если вылетел ниже мира (y - отрицательный) - уничтожаем самолет.
 * Если вылетел за края - возвращаем в пределы мира.
 */
public class EnemyPlaneOutOfWorldStrategy extends ReturnedOutOfWorldStrategy<Plane> {

	@Override
	public void activate(Plane plane, double worldWidth, double worldHeight) {
		if (plane.getY() <= 0.0) {
			plane.destroy();
		} else {
			super.activate(plane, worldWidth, worldHeight);
		}
	}
}
