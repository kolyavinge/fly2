package fly2.game.enemy;

import static fly2.game.frontend.Direction._UNDEFINED;
import fly2.unittest.framework.EnemyBrainTestCase;

public class NormalEnemyBrainTest extends EnemyBrainTestCase {

	private NormalEnemyBrain brain;

	public void setUp() {
		setBrain(new NormalEnemyBrain());
		createWorld(100.0, 200.0);
	}

	public void testFireNearPlanes() {
		setPlayerPosition(0, 0);
		setEnemyPosition(0, 4.0 * enemy.getHeight());
		assertFireAndDirection(FIRE, _UNDEFINED);
	}

	public void testFireNearPlanes2() {
		setPlayerPosition(0, 0);
		setEnemyPosition(player.getWidth() / 2.0, 4.0 * enemy.getHeight());
		assertFireAndDirection(FIRE, _UNDEFINED);
	}

	public void testFireNearPlanes3() {
		setPlayerPosition(20.0, 0.0);
		setEnemyPosition(20.0 - player.getWidth() / 2.0, 4.0 * enemy.getHeight());
		assertFireAndDirection(FIRE, _UNDEFINED);
	}

	public void testNoFireFarPlanes() {
		setPlayerPosition(0, 0);
		setEnemyPosition(0, 5.0 * enemy.getHeight());
		assertFireAndDirection(NO_FIRE, _UNDEFINED);
	}

	public void testNoFireFarPlanes2() {
		setPlayerPosition(0, 0);
		setEnemyPosition(player.getWidth(), 4.0 * enemy.getHeight());
		assertFireAndDirection(NO_FIRE, _UNDEFINED);
	}

	public void testNoFireFarPlanes3() {
		setPlayerPosition(20.0, 0.0);
		setEnemyPosition(20.0 - player.getWidth(), 4.0 * enemy.getHeight());
		assertFireAndDirection(NO_FIRE, _UNDEFINED);
	}

	public void testSetNullContext() {
		try {
			brain.setContext(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testActivateWithoutContext() {
		brain = new NormalEnemyBrain();

		try {
			brain.activate();
			fail();
		} catch (IllegalStateException exp) {
		}
	}
}
