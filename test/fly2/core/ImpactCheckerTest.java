package fly2.core;

import java.util.*;
import junit.framework.TestCase;

public class ImpactCheckerTest extends TestCase {

	private ImpactChecker impactChecker;
	private Collection<WorldItemTuple> result;
	private List<WorldItem> worldItems;

	public void setUp() {
		impactChecker = new ImpactChecker();
		worldItems = new ArrayList<WorldItem>();
	}

	public void testCheckImpactTwoItems() {
		WorldItem item1 = addWorldItem(0, 0, 10, 10);
		WorldItem item2 = addWorldItem(5, 5, 10, 10);
		checkImpact();
		resultSize(1);
		resultContainsTuple(item1, item2);
	}

	public void testCheckImpactTwoItemsTouch() {
		WorldItem item1 = addWorldItem(0, 0, 1, 1);
		WorldItem item2 = addWorldItem(1, 1, 1, 1);
		checkImpact();
		resultSize(1);
		resultContainsTuple(item1, item2);
	}

	public void testCheckImpactThreeItems() {
		WorldItem item1 = addWorldItem(0, 0, 2, 2);
		WorldItem item2 = addWorldItem(1, 1, 3, 3);
		WorldItem item3 = addWorldItem(3, 3, 2, 2);
		checkImpact();
		resultSize(2);
		resultContainsTuple(item1, item2);
		resultContainsTuple(item2, item3);
	}

	public void testCheckImpactThreeItemsSameCoords() {
		WorldItem item1 = addWorldItem(0, 0, 1, 1);
		WorldItem item2 = addWorldItem(0, 0, 1, 1);
		WorldItem item3 = addWorldItem(0, 0, 1, 1);
		checkImpact();
		resultSize(3);
		resultContainsTuple(item1, item2);
		resultContainsTuple(item1, item3);
		resultContainsTuple(item2, item3);
	}

	private void resultContainsTuple(WorldItem item1, WorldItem item2) {
		boolean tupleContains = false;

		for (WorldItemTuple tuple : result) {
			if (tuple.getFirst() == item1 && tuple.getSecond() == item2) {
				tupleContains = true;
				break;
			}
		}

		assertTrue(tupleContains);
	}

	public void testCheckImpactEmptyItems() {
		checkImpact();
		resultSize(0);
	}

	private void resultSize(int expectedResultSize) {
		assertEquals(expectedResultSize, result.size());
	}

	private void checkImpact() {
		result = impactChecker.checkImpact(worldItems);
	}

	private WorldItem addWorldItem(double leftUpX, double leftUpY, double width, double height) {
		WorldItem item = new WorldItem();
		item.setLeftUpPoint(leftUpX, leftUpY);
		item.setSize(width, height);
		worldItems.add(item);

		return item;
	}
}
