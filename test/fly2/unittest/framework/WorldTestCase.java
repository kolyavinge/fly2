package fly2.unittest.framework;

import fly2.phyzix.*;
import fly2.unittest.stub.UpdateableWorldItem;
import junit.framework.TestCase;

public class WorldTestCase extends TestCase {

	protected World world;
	protected boolean activateImpactFlag;

	protected UpdateableWorldItem addUpdateableWorldItem() {
		UpdateableWorldItem item = new UpdateableWorldItem();
		world.addItem(item);

		return item;
	}

	protected UpdateableWorldItem addUpdateableWorldItem(double x, double y, double width, double height) {
		UpdateableWorldItem item = new UpdateableWorldItem();
		item.setPosition(x, y);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	protected WorldItem addWorldItem(double x, double y, double width, double height) {
		WorldItem item = new WorldItem();
		item.setPosition(x, y);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	protected WorldItem addWorldItem() {
		return addWorldItem(0, 0, 1, 1);
	}

	public class TestImpactStrategy implements ImpactStrategy<WorldItem, WorldItem> {

		public Class<WorldItem> getFirstObjectClass() {
			return WorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(WorldItem left, WorldItem right) {
			activateImpactFlag = true;
		}
	}

	public class UpdateableWorldItemImpactStrategy implements ImpactStrategy<UpdateableWorldItem, WorldItem> {

		public Class<UpdateableWorldItem> getFirstObjectClass() {
			return UpdateableWorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(UpdateableWorldItem left, WorldItem right) {
			activateImpactFlag = true;
		}
	}

	public class BlankOutOfWorldStrategy implements OutOfWorldStrategy<WorldItem> {
		public void activate(WorldItem item, double worldWidth, double worldHeight) {
		}
	}
}
