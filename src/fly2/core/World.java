package fly2.core;

import java.util.*;

import fly2.common.*;

/**
 * Игровой мир. Обновляет объекты и запускает логику соударения.
 */
public final class World implements WorldItemCollection {

	private double width, height;
	private List<WorldItem> worldItems;
	private ImpactStrategyCollection impactStrategies;
	private ImpactChecker impactChecker;
	private boolean raiseErrorIfImpactStrategyNotFound;

	public World(double width, double height, ImpactChecker impactChecker) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Размеры игрового мира должны быть больше нуля");
		setImpactChecker(impactChecker);
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

	public ImpactChecker getImpactChecker() {
		return impactChecker;
	}

	public void setImpactChecker(ImpactChecker impactChecker) {
		if (impactChecker == null)
			throw new NullPointerException("impactChecker");

		this.impactChecker = impactChecker;
	}

	public void setRaiseErrorIfImpactStrategyNotFound(boolean value) {
		raiseErrorIfImpactStrategyNotFound = value;
	}

	public void updateItems() {
		for (WorldItem item : worldItems)
			if (item instanceof Updateable)
				((Updateable) item).update();
	}

	public void activateItemsImpact() {
		Collection<WorldItemTuple> impactedItemTuples = impactChecker.checkImpact(worldItems);

		for (WorldItemTuple t : impactedItemTuples) {
			if (isDestroyed(t.getFirst()) || isDestroyed(t.getSecond()))
				continue;

			Class firstClass = t.getFirst().getClass();
			Class secondClass = t.getSecond().getClass();

			if (impactStrategies.contains(firstClass, secondClass)) {
				ImpactStrategy strategy = impactStrategies.getFor(firstClass, secondClass);
				strategy.activateImpact(t.getFirst(), t.getSecond());

			} else if (impactStrategies.contains(secondClass, firstClass)) {
				ImpactStrategy strategy = impactStrategies.getFor(secondClass, firstClass);
				strategy.activateImpact(t.getSecond(), t.getFirst());

			} else if (raiseErrorIfImpactStrategyNotFound) {
				String message = String.format("Отсутствует объект ImpactStrategy для классов %s %s", firstClass.getName(), secondClass.getName());
				throw new NoSuchElementException(message);
			}
		}
	}

	public void removeDestroyedItems() {
		Iterator<WorldItem> iterator = worldItems.iterator();
		while (iterator.hasNext()) {
			WorldItem item = iterator.next();
			if (isDestroyed(item))
				iterator.remove();
		}
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

	private boolean isDestroyed(WorldItem item) {
		return (item instanceof Destroyable) && ((Destroyable) item).isDestroyed();
	}
}
