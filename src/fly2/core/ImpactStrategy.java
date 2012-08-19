package fly2.core;

public interface ImpactStrategy {

	Class getLeftObjectClass();

	Class getRightObjectClass();

	void activateImpact(Object leftObject, Object rightObject);
}
