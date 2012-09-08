package fly2.game.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import fly2.app.R;

public class LightBitmapFactory implements GameBitmapFactory {

	private Resources resources;

	public LightBitmapFactory(Resources resources) {
		this.resources = resources;
	}

	public Bitmap getBackgroundBitmap() {
		return BitmapFactory.decodeResource(resources, R.drawable.back_1);
	}

	public Bitmap getPlayerPlaneBitmap() {
		return BitmapFactory.decodeResource(resources, R.drawable.plane22);
	}
}
