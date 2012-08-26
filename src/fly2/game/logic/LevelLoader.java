package fly2.game.logic;

import fly2.game.frontend.*;
import fly2.phyzix.*;
import java.util.Collections;

public class LevelLoader {

	private final static int levelsCount = 1;

	private LevelContext levels[];

	public LevelLoader() {
		this.levels = new LevelContext[1];
		loadLevels();
	}

	public int getLevelsCount() {
		return levelsCount;
	}

	public LevelContext getLevelByNumber(int levelNumber) {
		if (1 <= levelNumber && levelNumber <= levelsCount) {
			return levels[levelNumber - 1];
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void loadLevels() {
		final GameWorld world = new GameWorld(20.0, 100.0);

//		Weapon weapon = new Weapon(world);
//
//		final Plane plane = new Plane(weapon);
//		plane.setPosition((world.getWidth() - plane.getWidth()) / 2.0, 0);
//		plane.setHealth(10);
//
//		levels[0] = new LevelContext() {
//
//			public Plane getPlayerPlane() {
//				return plane;
//			}
//
//			public Iterable<fly2.game.frontend.Plane> getEnemyPlanes() {
//				return Collections.emptyList();
//			}
//
//			public int getEnemyPlanesCount() {
//				return 0;
//			}
//
//			public fly2.game.frontend.GameWorld getWorld() {
//				return gameWorld;
//			}
//		};
	}
}
