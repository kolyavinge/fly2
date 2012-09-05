package fly2.view.common;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import javax.microedition.khronos.opengles.GL10;
import static javax.microedition.khronos.opengles.GL10.*;

public class TextureLoader {

	public static int fromBitmap(GL10 gl, Bitmap bitmap) {
		int[] textureId = new int[1];
		gl.glGenTextures(1, textureId, 0);
		gl.glBindTexture(GL_TEXTURE_2D, textureId[0]);
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

		return textureId[0];
	}
}
