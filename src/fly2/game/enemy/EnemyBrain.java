package fly2.game.enemy;

import android.content.Context;
import fly2.game.frontend.Bullet;
import fly2.game.frontend.GameWorld;
import fly2.game.frontend.Plane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

public class EnemyBrain {

	private GameWorld gameWorld;
	private Plane player;
	private Plane enemy;
	private Collection<Plane> enemyPlanes;
	private Collection<Bullet> bullets;
	private StepResult stepResult;
	
	private ScriptManager script;
	private String scriptText;

	public EnemyBrain(Context context) {
		scriptText = readScriptSourceText(context);
		script = new ScriptManager();
	}

	private String readScriptSourceText(Context context) {
		try {
			InputStream is = context.getAssets().open("brain.js");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuilder scriptText = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				scriptText.append(line);
				scriptText.append(System.getProperty("line.separator"));
			}

			return scriptText.toString();
			
		} catch (IOException ioexp) {
			throw new RuntimeException("Can't read file 'brain.js'");
		}
	}

	public StepResult activate() {
		script.define("world", gameWorld);
		script.define("player", player);
		script.define("enemy", enemy);
		script.define("enemies", enemyPlanes);
		script.define("bullets", bullets);
		script.define("stepResult", stepResult);
		
		script.evaluate(scriptText);
		
		return stepResult;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public void setPlayer(Plane player) {
		this.player = player;
	}

	public void setEnemy(Plane enemy) {
		this.enemy = enemy;
	}

	public void setEnemyPlanes(Collection<Plane> enemyPlanes) {
		this.enemyPlanes = enemyPlanes;
	}

	public void setBullets(Collection<Bullet> bullets) {
		this.bullets = bullets;
	}

	public void setStepResult(StepResult stepResult) {
		this.stepResult = stepResult;
	}
}
