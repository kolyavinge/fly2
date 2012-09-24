package fly2.game.logic;

import static fly2.common.Direction.*;
import fly2.game.enemy.EnemyBrainNotInitializeException;
import fly2.game.enemy.StepResult;
import fly2.unittest.TestEnemyBrain;
import junit.framework.TestCase;

public class EnemyPlaneTest extends TestCase {

	private EnemyPlane enemy;
	private TestEnemyBrain brain;

	public void setUp() {
		brain = new TestEnemyBrain();
		enemy = new EnemyPlane();
		enemy.setWeapon(new Weapon());
		enemy.setBrain(brain);
		enemy.setFlyDirection(DOWN);
	}

	public void testApplyStepResult() {
		StepResult stepResult = new StepResult();
		stepResult.fire();
		stepResult.moveRight();
		brain.setStepResult(stepResult);
		enemy.setPosition(50.0, 20.0);

		enemy.update();

		assertEquals(enemy.getMoveSpeed(), enemy.getX() - 50.0, 0.001);
		assertEquals(20.0, enemy.getY(), 0.001);
	}

	public void testApplyStepResult2() {
		StepResult stepResult = new StepResult();
		stepResult.moveLeft();
		brain.setStepResult(stepResult);
		enemy.setPosition(50.0, 20.0);

		enemy.update();

		assertEquals(-enemy.getMoveSpeed(), enemy.getX() - 50.0, 0.001);
		assertEquals(20.0, enemy.getY(), 0.001);
	}

	public void testApplyStepResult3() {
		brain.setStepResult(new StepResult());
		enemy.setPosition(0.0, 0.0);

		enemy.update();

		assertEquals(0.0, enemy.getX());
		assertEquals(0.0, enemy.getY());
	}
	
	public void testBrainNotInit() {
		enemy = new EnemyPlane();
		enemy.setFlyDirection(DOWN);

		try {
			enemy.update();
			fail();
		} catch (EnemyBrainNotInitializeException exp) {
		}
	}

	public void testNullBrain() {
		enemy = new EnemyPlane();

		try {
			enemy.setBrain(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testNullBrainContext() {
		enemy = new EnemyPlane();

		try {
			enemy.setBrainContext(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}
}
