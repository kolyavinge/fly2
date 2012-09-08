package fly2.game.view;

import javax.microedition.khronos.opengles.GL10;

public interface GameObjectView<TGameModelObject> {

	TGameModelObject getGameModelObject();
	
	void draw(GL10 gl);
	
	boolean isDestroyed();
}
