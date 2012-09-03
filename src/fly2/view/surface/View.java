package fly2.view.surface;

import javax.microedition.khronos.opengles.GL10;

public interface View<TGameModelObject> {

	TGameModelObject getGameModelObject();
	
	void draw(GL10 gl);
	
	boolean isDestroyed();
}
