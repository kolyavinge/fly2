package fly2.unittest;

import fly2.core.*;
import junit.framework.TestCase;

public class WorldTestCase extends TestCase {

	protected World world;
	protected boolean activateImpactFlag;

	protected UpdateableWorldItem addUpdateableWorldItem() {
		UpdateableWorldItem item = new UpdateableWorldItem();
		world.addItem(item);

		return item;
	}

	protected UpdateableWorldItem addUpdateableWorldItem(double leftUpX, double leftUpY, double width, double height) {
		UpdateableWorldItem item = new UpdateableWorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	protected DestroyableWorldItem addDestroyableWorldItem() {
		DestroyableWorldItem item = new DestroyableWorldItem();
		world.addItem(item);

		return item;
	}

	protected DestroyableWorldItem addDestroyableWorldItem(double leftUpX, double leftUpY, double width, double height) {
		DestroyableWorldItem item = new DestroyableWorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		world.addItem(item);

		return item;
	}

	protected WorldItem addWorldItem(double leftUpX, double leftUpY, double width, double height) {
		WorldItem item = new WorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		world.addItem(item);

		return item;
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

	public class DestroyableWorldItemImpactStrategy implements ImpactStrategy<DestroyableWorldItem, WorldItem> {

		public Class<DestroyableWorldItem> getFirstObjectClass() {
			return DestroyableWorldItem.class;
		}

		public Class<WorldItem> getSecondObjectClass() {
			return WorldItem.class;
		}

		public void activateImpact(DestroyableWorldItem left, WorldItem right) {
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
}
