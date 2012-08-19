package fly2.unittest;

import java.util.*;
import fly2.core.*;

public class TestWorldItemCollection implements WorldItemCollection {

	private List<WorldItem> list = new ArrayList<WorldItem>();

	public void addItem(WorldItem item) {
		list.add(item);
	}

	public void removeItem(WorldItem item) {
		list.remove(item);
	}

	public Iterable<WorldItem> getItems() {
		return list;
	}

	public int getItemsCount() {
		return list.size();
	}
}
