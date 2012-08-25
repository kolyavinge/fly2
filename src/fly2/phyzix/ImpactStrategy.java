package fly2.phyzix;

/**
 * Реализует логику соударения разных игровых объектов друг с другом
 */
public interface ImpactStrategy<T extends WorldItem, U extends WorldItem> {

	/**
	 * Класс первого игрового объекта, для которого предназначена данная стратегия
	 */
	Class<T> getFirstObjectClass();

	/**
	 * Класс второго игрового объекта, для которого предназначена данная стратегия
	 */
	Class<U> getSecondObjectClass();

	/**
	 * Активирует логику соударения объектов
	 */
	void activateImpact(T first, U second);
}
