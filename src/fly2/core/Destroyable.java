package fly2.core;

/**
 * Уничтожаемый игровой объект
 */
public interface Destroyable {

	/**
	 * Уничтожен объект или нет
	 */
	boolean isDestroyed();
	
	/**
	 * Уничтожить объект
	 */
	void destroy();
}
