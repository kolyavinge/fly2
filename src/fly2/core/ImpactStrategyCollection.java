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

	public void add(ImpactStrategy impactStrategy) {
		Class leftClass = impactStrategy.getFirstObjectClass();
		Class rightClass = impactStrategy.getSecondObjectClass();

		if (contains(leftClass, rightClass)) {
			String message = String.format("%s %s", leftClass.getName(), rightClass.getName());
			throw new DuplicateKeyException(message);
		}

		items.add(impactStrategy);
	}

	public void remove(ImpactStrategy impactStrategy) {
		items.remove(impactStrategy);
	}

	public int size() {
		return items.size();
	}

	public boolean contains(Class left, Class right) {
		for (ImpactStrategy impactStrategy : items) {
			if (impactStrategy.getFirstObjectClass() == left && impactStrategy.getSecondObjectClass() == right)
				return true;
		}

		return false;
	}

	public ImpactStrategy getFor(Class left, Class right) {
		for (ImpactStrategy impactStrategy : items) {
			if (impactStrategy.getFirstObjectClass() == left && impactStrategy.getSecondObjectClass() == right)
				return impactStrategy;
		}

		throw new NoSuchElementException();
	}
}
