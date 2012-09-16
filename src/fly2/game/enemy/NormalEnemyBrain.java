package fly2.game.enemy;

import fly2.game.common.ResourceFileReader;

import java.io.IOException;

public class NormalEnemyBrain extends EnemyBrain {

	private static final String brainScriptFileName = "brain.js";

	private ScriptManager script;
	private String scriptText;

	public NormalEnemyBrain(ResourceFileReader fileReader) {
		readScriptFileText(fileReader);
		script = new ScriptManager();
	}

	@Override
	public void activate() {
		script.define("world", getGameWorld());
		script.define("player", getPlayer());
		script.define("enemy", getEnemy());
		script.define("enemies", getEnemyPlanes());
		script.define("bullets", getBullets());
		script.define("stepResult", getStepResult());

		script.evaluate(scriptText);
	}

	private void readScriptFileText(ResourceFileReader fileReader) {
		try {
			scriptText = fileReader.getFileText(brainScriptFileName);
		} catch (IOException ioexp) {
			throw new RuntimeException("Can't read file " + brainScriptFileName);
		}
	}
}
