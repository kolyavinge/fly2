package fly2.core;

public interface WorldItemCollection {

	void addItem(WorldItem item);

	void removeItem(WorldItem item);

	Iterable<WorldItem> getItems();

	int getItemsCount();
}
