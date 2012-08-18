package fly2.core;

import java.util.*;
import fly2.common.*;

public class World {

	private double width, height;
	private List<WorldItem> items;
	private List<Updateable> updateables;

	public World(double width, double height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Размеры игрового мира должны быть больше нуля");
		this.width = width;
		this.height = height;
		this.items = new ArrayList<WorldItem>(32);
		this.updateables = new ArrayList<Updateable>(32);
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

		items.add(item);

		if (item instanceof Updateable)
			updateables.add((Updateable) item);
	}

	private boolean checkBounds(WorldItem item) {
		Rectangle b = item.getBounds();
		return Geometry.innerRect(
				0.0, 0.0, width, height,
				b.getLeftUpX(), b.getLeftUpY(), b.getWidth(), b.getHeight());
	}

	public void removeItem(WorldItem item) {
		items.remove(item);
	}

	public Iterable<WorldItem> getItems() {
		return items;
	}

	public int getItemsCount() {
		return items.size();
	}

	public void update() {
		updateAllItems();
	}

	private void updateAllItems() {
		for (Updateable u : updateables)
			u.update();
	}
}
