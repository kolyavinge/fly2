package fly2.common.android;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceFileReader {

	private AssetManager assetManager;

	public ResourceFileReader(AssetManager assetManager) {
		if (assetManager == null)
			throw new NullPointerException("assetManager");
			
		this.assetManager = assetManager;
	}

	public String getFileText(String fullFilePath) throws IOException {
		InputStream is = assetManager.open(fullFilePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder scriptText = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			scriptText.append(line).append(System.getProperty("line.separator"));
		}

		return scriptText.toString();
	}
}
