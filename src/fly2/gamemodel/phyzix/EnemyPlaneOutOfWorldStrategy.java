package fly2.gamemodel.phyzix;

import fly2.phyzix.ext.ReturnedOutOfWorldStrategy;

/**
 * Определяет поведение вражеского самолетика, когда он вылетает за пределы игрового мира.
 * Если вылетел ниже мира (y - отрицательный) - уничтожаем самолет.
 * Если вылетел за края - возвращаем в пределы мира.
 */
public class EnemyPlaneOutOfWorldStrategy extends ReturnedOutOfWorldStrategy<PlaneWorldItem> {

	@Override
	public void activate(PlaneWorldItem plane, double worldWidth, double worldHeight) {
		if (plane.getY() <= 0.0) {
			plane.destroy();
		} else {
			super.activate(plane, worldWidth, worldHeight);
		}
	}
}
