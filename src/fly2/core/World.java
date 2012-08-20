package fly2.core;

import java.util.*;

import fly2.common.*;

/**
 * Игровой мир. Обновляет объекты и запускает логику соударения.
 */
public class World implements WorldItemCollection {

	private double width, height;
	private List<WorldItem> worldItems;
	private ImpactStrategyCollection impactStrategies;
	private ImpactChecker impactChecker;
	private boolean raiseErrorIfImpactStrategyNotFound;

	public World(double width, double height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Размеры игрового мира должны быть больше нуля");
		this.width = width;
		this.height = height;
		this.worldItems = new ArrayList<WorldItem>();
		this.impactStrategies = new ImpactStrategyCollection();
		this.raiseErrorIfImpactStrategyNotFound = false;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void addItem(WorldItem item) {
		worldItems.add(item);
	}

	public void removeItem(WorldItem item) {
		worldItems.remove(item);
	}

	public Iterable<WorldItem> getItems() {
		return worldItems;
	}

	public int getItemsCount() {
		return worldItems.size();
	}

	public void registerImpactStrategy(ImpactStrategy impactStrategy) {
		impactStrategies.add(impactStrategy);
	}

	public void setImpactChecker(ImpactChecker impactChecker) {
		if (impactChecker == null)
			throw new NullPointerException("impactChecker");

		this.impactChecker = impactChecker;
	}

	public void setRaiseErrorIfImpactStrategyNotFound(boolean value) {
		raiseErrorIfImpactStrategyNotFound = value;
	}

	public void removeOutOfWorldItems() {
		Iterator<WorldItem> iterator = worldItems.iterator();
		while (iterator.hasNext()) {
			WorldItem item = iterator.next();
			if (inWorld(item) == false)
				iterator.remove();
		}
	}

	private boolean inWorld(WorldItem item) {
		Bounds bounds = item.getBounds();
		return Geometry.innerRect(
				0.0, 0.0, width, height,
				bounds.getLeftUpX(), bounds.getLeftUpY(), item.getWidth(), item.getHeight());
	}

	public void updateAllItems() {
		for (WorldItem item : worldItems)
			if (item instanceof Updateable)
				((Updateable) item).update();
	}

	public void activateItemsImpact() {
		if (impactChecker == null)
			throw new IllegalStateException("Объект типа ImpactChecker не задан");

		Collection<WorldItemTuple> impactedItemTuples = impactChecker.checkImpact(worldItems);

		for (WorldItemTuple t : impactedItemTuples) {
			Class firstClass = t.getFirst().getClass();
			Class secondClass = t.getSecond().getClass();

			if (impactStrategies.contains(firstClass, secondClass)) {
				ImpactStrategy strategy = impactStrategies.getFor(firstClass, secondClass);
				strategy.activateImpact(t.getFirst(), t.getSecond());
			} else {
				if (raiseErrorIfImpactStrategyNotFound) {
					String message = String.format("Отсутствует объект ImpactStrategy для классов %s %s", firstClass.getName(), secondClass.getName());
					throw new NoSuchElementException(message);
				}
			}
		}
	}
}
