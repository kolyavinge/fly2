package fly2.core;

import java.util.*;

import fly2.common.*;

public class World {

	private double width, height;
	private List<WorldItem> worldItems;
	private ImpactStrategyCollection impactStrategies;
	private ImpactChecker impactChecker;

	public World(double width, double height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Размеры игрового мира должны быть больше нуля");
		this.width = width;
		this.height = height;
		this.worldItems = new ArrayList<WorldItem>();
		this.impactStrategies = new ImpactStrategyCollection();
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void addItem(WorldItem item) {
		if (checkBounds(item) == false)
			throw new ItemOutOfWorldException(item);

		worldItems.add(item);
	}

	private boolean checkBounds(WorldItem item) {
		Rectangle b = item.getBounds();
		return Geometry.innerRect(
				0.0, 0.0, width, height,
				b.getLeftUpX(), b.getLeftUpY(), b.getWidth(), b.getHeight());
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

	public void updateAllItems() {
		for (WorldItem item : worldItems)
			if (item instanceof Updateable)
				((Updateable) item).update();
	}

	public void activateItemsImpact() {
		if (impactChecker == null)
			throw new IllegalStateException();

		Collection<WorldItemTuple> impactedItemTuples = impactChecker.checkImpact(worldItems);

		for (WorldItemTuple t : impactedItemTuples) {
			Class firstClass = t.getFirst().getClass();
			Class secondClass = t.getSecond().getClass();

			if (impactStrategies.contains(firstClass, secondClass) == false) {
				String message = String.format("Отсутствует объект ImpactStrategy для классов %s %s", firstClass.getName(), secondClass.getName());
				throw new NoSuchElementException(message);
			}

			ImpactStrategy strategy = impactStrategies.getFor(firstClass, secondClass);
			strategy.activateImpact(t.getFirst(), t.getSecond());
		}
	}
}
