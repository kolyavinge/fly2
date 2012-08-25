package fly2.phyzix;

/**
 * Содержит алгоритм, который срабатывает, когда объект вылетает за игровой мир
 */
public interface OutOfWorldStrategy<T extends WorldItem> {

	void activate(T item, double worldWidth, double worldHeight);
}
