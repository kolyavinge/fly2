package fly2.game.enemy;

import static fly2.common.Direction.*;
import android.test.InstrumentationTestCase;
import fly2.common.Direction;
import fly2.game.logic.Plane;

import java.util.Collection;
import java.util.Collections;

public class EnemyBrainTest extends InstrumentationTestCase {

	private static final int FIRE = 1;
	private static final int NO_FIRE = 2;

	private EnemyBrain brain;
	private fly2.game.logic.GameWorld gameWorld;
	private Plane player;
	private Plane enemy;
	private StepResult stepResult;

	public void setUp() {
		stepResult = new StepResult();
		gameWorld = new fly2.game.logic.GameWorld(100, 200);
		player = gameWorld.getPlayerPlane();
		brain = new EnemyBrain(getInstrumentation().getContext());
	}

	public void testFireNear() {
		player.setPosition(0, 0);
		enemy = createEnemyPlane();
		enemy.setPosition(0, 4.0 * enemy.getHeight());
		assertFireAndDirection(FIRE, _UNDEFINED);
	}

	public void testFireFar() {
		player.setPosition(0, 0);
		enemy = createEnemyPlane();
		enemy.setPosition(0, 5.0 * enemy.getHeight());
		assertFireAndDirection(NO_FIRE, _UNDEFINED);
	}

	private Plane createEnemyPlane() {
		return gameWorld.createEnemyPlane();
	}

	private void assertFireAndDirection(int fire, Direction moveDirection) {
		Collection<fly2.game.frontend.Plane> enemyPlanes = Collections.<fly2.game.frontend.Plane> unmodifiableCollection(gameWorld.getEnemyPlanes());
		Collection<fly2.game.frontend.Bullet> bullets = Collections.<fly2.game.frontend.Bullet> unmodifiableCollection(gameWorld.getBullets());

		brain.setGameWorld(gameWorld);
		brain.setPlayer(player);
		brain.setEnemy(enemy);
		brain.setEnemyPlanes(enemyPlanes);
		brain.setBullets(bullets);
		brain.setStepResult(stepResult);

		brain.activate();

		if (fire == FIRE) {
			assertTrue(stepResult.isFire());
		} else if (fire == NO_FIRE) {
			assertFalse(stepResult.isFire());
		} else {
			throw new RuntimeException();
		}

		assertEquals(_UNDEFINED, stepResult.getMoveDirection());
	}
}
