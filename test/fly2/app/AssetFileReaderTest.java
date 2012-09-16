package fly2.app;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import fly2.app.AssetFileReader;

import java.io.IOException;

public class AssetFileReaderTest extends InstrumentationTestCase {

	private AssetFileReader reader;

	public void setUp() {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		reader = new AssetFileReader(assetManager);
	}

	public void testGetFileText() throws IOException {
		String expected = "test file\n123\n\n";
		String actual = reader.getFileText("testFile");
		assertEquals(expected, actual);
	}

	public void testFileNotFound() {
		try {
			reader.getFileText("file_not_found");
			fail();
		} catch (IOException exp) {
		}
	}
}
