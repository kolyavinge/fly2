package fly2.gamemodel;

/**
 * Загрузчик раундов.
 */
public interface LevelLoader {

	/**
	 * Возвращает общее кол-во раундов.
	 */
	int getLevelsCount();

	/**
	 * Возвращает раунд по его номеру.
	 * Нумерация начининается с 1-цы.
	 */
	LevelContext getLevelByNumber(int levelNumber);
}
