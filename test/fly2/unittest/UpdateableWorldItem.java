package fly2.unittest;

import fly2.core.*;

public class UpdateableWorldItem extends WorldItem implements Updateable {

	private boolean isUpdated = false;

	public boolean isUpdated() {
		return isUpdated;
	}

	public void update() {
		isUpdated = true;
	}
}
