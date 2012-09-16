package fly2.app;

import android.content.res.AssetManager;
import fly2.game.common.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetFileReader implements ResourceFileReader {

	private AssetManager assetManager;

	public AssetFileReader(AssetManager assetManager) {
		if (assetManager == null)
			throw new NullPointerException("assetManager");
			
		this.assetManager = assetManager;
	}

	public String getFileText(String fileName) throws IOException {
		InputStream is = assetManager.open(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder scriptText = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			scriptText.append(line).append(System.getProperty("line.separator"));
		}

		return scriptText.toString();
	}
}
