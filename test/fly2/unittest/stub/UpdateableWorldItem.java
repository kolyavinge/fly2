package fly2.unittest.stub;

import fly2.phyzix.*;

public class UpdateableWorldItem extends WorldItem implements Updateable {

	private boolean isUpdated = false;

	public boolean isUpdated() {
		return isUpdated;
	}

	public void update() {
		isUpdated = true;
	}
}
