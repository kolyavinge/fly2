package fly2.core;

/**
 * Реализует логику соударения разных игровых объектов друг с другом
 */
public interface ImpactStrategy {

	/**
	 * Класс первого игрового объекта, для которого предназначена данная стратегия
	 */
	Class getFirstObjectClass();

	/**
	 * Класс второго игрового объекта, для которого предназначена данная стратегия
	 */
	Class getSecondObjectClass();

	/**
	 * Активирует логику соударения объектов
	 */
	void activateImpact(Object first, Object second);
}
