package fly2.core;

public class NoSuchWorldItemImpactStrategy extends RuntimeException {

	private static final long serialVersionUID = 918685482959868123L;

	public NoSuchWorldItemImpactStrategy(WorldItem first, WorldItem second) {
		super(String.format(
				"Отсутствует объект ImpactStrategy для классов %s %s",
				first.getClass().getName(), second.getClass().getName()));
	}
}
