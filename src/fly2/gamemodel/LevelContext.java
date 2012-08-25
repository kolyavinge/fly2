package fly2.gamemodel;

/**
 * Информация об игровом уровне
 */
public interface LevelContext {

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
