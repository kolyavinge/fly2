package fly2.unittest;

import fly2.game.common.ResourceFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestResourceFileReader implements ResourceFileReader {

	private Map<String, String> files = new HashMap<String, String>();

	public void put(String fileName, String fileContent) {
		files.put(fileName, fileContent);
	}

	public String getFileText(String fileName) throws IOException {
		return files.get(fileName);
	}
}
