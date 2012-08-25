package fly2.unittest;

import fly2.phyzix.*;

public class DestroyableWorldItem extends WorldItem implements Destroyable {

	private boolean isDestroyed = false;

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void destroy() {
		isDestroyed = true;
	}
}
