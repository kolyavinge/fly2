package fly2.core;

import fly2.common.DuplicateKeyException;

import java.util.*;

public class ImpactStrategyCollection {

	private List<ImpactStrategy> items;

	public ImpactStrategyCollection() {
		items = new ArrayList<ImpactStrategy>();
	}

	public void add(ImpactStrategy impactStrategy) {
		Class leftClass = impactStrategy.getLeftObjectClass();
		Class rightClass = impactStrategy.getRightObjectClass();

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
			if (impactStrategy.getLeftObjectClass() == left && impactStrategy.getRightObjectClass() == right)
				return true;
		}

		return false;
	}

	public ImpactStrategy getFor(Class left, Class right) {
		for (ImpactStrategy impactStrategy : items) {
			if (impactStrategy.getLeftObjectClass() == left && impactStrategy.getRightObjectClass() == right)
				return impactStrategy;
		}

		throw new NoSuchElementException();
	}
}
