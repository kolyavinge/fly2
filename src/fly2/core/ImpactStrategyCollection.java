package fly2.core;

import fly2.common.*;
import java.util.*;

/**
 * Коллекция объектов типа ImpactStrategy
 */
public class ImpactStrategyCollection {

	private SparseMatrix<Class, Class, ImpactStrategy> items;
	private int itemsCount;

	public ImpactStrategyCollection() {
		items = new SparseMatrix<Class, Class, ImpactStrategy>();
		itemsCount = 0;
	}

	public <T extends WorldItem, U extends WorldItem> void add(ImpactStrategy<T, U> impactStrategy) {
		Class<T> left = impactStrategy.getFirstObjectClass();
		Class<U> right = impactStrategy.getSecondObjectClass();

		if (contains(left, right)) {
			String message = String.format("%s %s", left.getName(), right.getName());
			throw new DuplicateKeyException(message);
		}

		items.add(left, right, impactStrategy);
		if (left != right)
			items.add(right, left, impactStrategy);

		itemsCount++;
	}

	public <T extends WorldItem, U extends WorldItem> void remove(ImpactStrategy<T, U> impactStrategy) {
		Class<T> left = impactStrategy.getFirstObjectClass();
		Class<U> right = impactStrategy.getSecondObjectClass();
		items.remove(left, right);
		itemsCount--;
	}

	public int size() {
		return itemsCount;
	}

	public <T extends WorldItem, U extends WorldItem> boolean contains(Class<T> first, Class<U> second) {
		return items.contains(first, second) || items.contains(second, first);
	}

	public <T extends WorldItem, U extends WorldItem> ImpactStrategy<T, U> getFor(Class<T> first, Class<U> second) {
		return items.get(first, second);
	}
}
