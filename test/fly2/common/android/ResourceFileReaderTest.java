package fly2.common.android;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import java.io.IOException;

public class ResourceFileReaderTest extends InstrumentationTestCase {

	private ResourceFileReader reader;

	public void setUp() {
		AssetManager assetManager = getInstrumentation().getContext().getAssets();
		reader = new ResourceFileReader(assetManager);
	}

	public void testGetFileText() throws IOException {
		String expected = "test file\n123\n\n";
		String actual = reader.getFileText("testFile");
		assertEquals(expected, actual);
	}
}
