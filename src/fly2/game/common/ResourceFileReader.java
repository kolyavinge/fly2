package fly2.game.common;

import java.io.IOException;

public interface ResourceFileReader {

	String getFileText(String fileName) throws IOException;
}
