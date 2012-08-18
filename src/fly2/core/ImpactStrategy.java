package fly2.core;

public interface ImpactStrategy {

	Class getLeftObjectClass();

	Class getRightObjectClass();

	void impact(Object leftObject, Object rightObject);
}
