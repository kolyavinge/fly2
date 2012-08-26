package fly2.phyzix;

import java.util.*;
import fly2.common.*;

/**
 * Игровой мир. Обновляет объекты и запускает логику соударения.
 */
public final class World implements WorldItemCollection {

	private double width, height;
	private List<WorldItem> worldItems;
	private Map<WorldItem, OutOfWorldStrategy> outOfWorldStrategies;
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
		this.outOfWorldStrategies = new HashMap<WorldItem, OutOfWorldStrategy>();
		this.impactChecker = new ImpactChecker();
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

	public <T extends WorldItem, U extends WorldItem> void registerImpactStrategy(ImpactStrategy<T, U> impactStrategy) {
		impactStrategies.add(impactStrategy);
	}

	public void registerOutOfWorldStrategy(WorldItem item, OutOfWorldStrategy strategy) {
		outOfWorldStrategies.put(item, strategy);
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
		Collection<WorldItemTuple> impactedItems = impactChecker.checkImpact(worldItems);
		for (WorldItemTuple t : impactedItems) {
			if (notDestroyed(t.getFirst()) && notDestroyed(t.getSecond()) && inWorld(t.getFirst()) && inWorld(t.getSecond()))
				activateImpactStrategyOrRaiseError(t.getFirst(), t.getSecond());
		}
	}

	private void activateImpactStrategyOrRaiseError(WorldItem first, WorldItem second) {
		boolean activated = tryActivateImpactStrategy(first, second) || tryActivateImpactStrategy(second, first);
		if (!activated && raiseErrorIfImpactStrategyNotFound)
			throw new NoSuchWorldItemImpactStrategy(first, second);
	}

	private boolean tryActivateImpactStrategy(WorldItem first, WorldItem second) {
		boolean founded = impactStrategies.contains(first.getClass(), second.getClass());
		if (founded) {
			ImpactStrategy strategy = impactStrategies.getFor(first.getClass(), second.getClass());
			strategy.activateImpact(first, second);
		}

		return founded;
	}

	public void removeDestroyedItems() {
		Iterator<WorldItem> iterator = worldItems.iterator();
		while (iterator.hasNext()) {
			WorldItem item = iterator.next();
			if (isDestroyed(item))
				iterator.remove();
		}
	}

	public void activateOutOfWorldItems() {
		Iterator<WorldItem> iterator = worldItems.iterator();
		while (iterator.hasNext()) {
			WorldItem item = iterator.next();
			if (inWorld(item) == false) {
				if (outOfWorldStrategies.containsKey(item)) {
					outOfWorldStrategies.get(item).activate(item, width, height);
				} else {
					iterator.remove();
				}
			}
		}
	}

	public boolean inWorld(WorldItem item) {
		return Geometry.innerRect(
				0.0, 0.0, width, height,
				item.getX(), item.getY(), item.getWidth(), item.getHeight());
	}

	private boolean notDestroyed(WorldItem item) {
		return isDestroyed(item) == false;
	}

	private boolean isDestroyed(WorldItem item) {
		return (item instanceof Destroyable) && ((Destroyable) item).isDestroyed();
	}
}
