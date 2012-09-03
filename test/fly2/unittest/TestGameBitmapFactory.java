package fly2.unittest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import fly2.view.common.GameBitmapFactory;

public class TestGameBitmapFactory implements GameBitmapFactory {

	public Bitmap getPlayerPlaneBitmap() {
		return BitmapFactory.decodeFile("fly2/test/test_image.png");
	}
}
