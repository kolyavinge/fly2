package fly2.view.common;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import fly2.app.R;

public class LightBitmapFactory implements GameBitmapFactory {

	private Resources resources;

	public LightBitmapFactory(Resources resources) {
		this.resources = resources;
	}

	public Bitmap getPlayerPlaneBitmap() {
		return BitmapFactory.decodeResource(resources, R.drawable.plane22);
	}
}
