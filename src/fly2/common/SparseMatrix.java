package fly2.common;

import java.util.*;

/**
 * Разреженная матричка
 */
public class SparseMatrix<TRowKey, TColKey, TValue> {

	private Map<TRowKey, Map<TColKey, TValue>> map;

	public SparseMatrix() {
		map = new HashMap<TRowKey, Map<TColKey, TValue>>();
	}

	public TValue get(TRowKey rowKey, TColKey colKey) {
		if (map.containsKey(rowKey) == false)
			throw new NoSuchElementException();

		Map<TColKey, TValue> rowMap = map.get(rowKey);
		if (rowMap.containsKey(colKey) == false)
			throw new NoSuchElementException();

		TValue value = rowMap.get(colKey);

		return value;
	}

	public void add(TRowKey rowKey, TColKey colKey, TValue value) {
		Map<TColKey, TValue> rowMap;

		if (map.containsKey(rowKey)) {
			rowMap = map.get(rowKey);

			if (rowMap.containsKey(colKey))
				throw new DuplicateKeyException();

			rowMap.put(colKey, value);

		} else {
			rowMap = new HashMap<TColKey, TValue>();
			rowMap.put(colKey, value);
			map.put(rowKey, rowMap);
		}
	}

	public void remove(TRowKey rowKey, TColKey colKey) {
		if (map.containsKey(rowKey) == false)
			throw new NoSuchElementException();

		Map<TColKey, TValue> rowMap = map.get(rowKey);
		if (rowMap.containsKey(colKey) == false)
			throw new NoSuchElementException();

		rowMap.remove(colKey);
	}

	public boolean contains(TRowKey rowKey, TColKey colKey) {
		if (map.containsKey(rowKey) == false)
			return false;

		return map.get(rowKey).containsKey(colKey);
	}
}
