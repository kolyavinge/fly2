package fly2.core;

import fly2.common.DuplicateKeyException;

import java.util.*;

/**
 * Коллекция объектов типа ImpactStrategy
 */
public class ImpactStrategyCollection {

	private List<ImpactStrategy> items;

	public ImpactStrategyCollection() {
		items = new ArrayList<ImpactStrategy>();
	}

	public <T extends WorldItem, U extends WorldItem> void add(ImpactStrategy<T, U> impactStrategy) {
		Class<T> left = impactStrategy.getFirstObjectClass();
		Class<U> right = impactStrategy.getSecondObjectClass();

		if (contains(left, right)) {
			String message = String.format("%s %s", left.getName(), right.getName());
			throw new DuplicateKeyException(message);
		}

		items.add(impactStrategy);
	}

	public <T extends WorldItem, U extends WorldItem> void remove(ImpactStrategy<T, U> impactStrategy) {
		items.remove(impactStrategy);
	}

	public int size() {
		return items.size();
	}

	public <T extends WorldItem, U extends WorldItem> boolean contains(Class<T> first, Class<U> second) {
		for (ImpactStrategy impactStrategy : items) {
			if (impactStrategy.getFirstObjectClass() == first && impactStrategy.getSecondObjectClass() == second)
				return true;
		}

		return false;
	}

	public <T extends WorldItem, U extends WorldItem> ImpactStrategy<T, U> getFor(Class<T> first, Class<U> second) {
		for (ImpactStrategy impactStrategy : items) {
			if (impactStrategy.getFirstObjectClass() == first && impactStrategy.getSecondObjectClass() == second)
				return impactStrategy;
		}

		throw new NoSuchElementException();
	}
}
