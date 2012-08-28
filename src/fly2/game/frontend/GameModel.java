package fly2.game.frontend;

/**
 * Модель игры
 */
public interface GameModel {

	/**
	 * Самолет игрока для данного уровня
	 */
	Plane getPlayerPlane();

	/**
	 * Самолеты ботов для данного уровня
	 */
	Iterable<Plane> getEnemyPlanes();
	
	/**
	 * Кол-во самолетов ботов
	 */
	int getEnemyPlanesCount();

	/**
	 * Игровом мир для данного уровня
	 */
	GameWorld getWorld();
}
