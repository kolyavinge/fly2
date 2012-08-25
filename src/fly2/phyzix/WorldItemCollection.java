package fly2.phyzix;

public interface WorldItemCollection {

	void addItem(WorldItem item);

	void removeItem(WorldItem item);

	Iterable<WorldItem> getItems();

	int getItemsCount();
}
