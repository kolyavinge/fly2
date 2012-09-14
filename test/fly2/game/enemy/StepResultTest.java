package fly2.game.enemy;

import junit.framework.TestCase;
import static fly2.common.Direction.*;

public class StepResultTest extends TestCase {

	private StepResult stepResult;

	public void setUp() {
		stepResult = new StepResult();
	}

	public void testNew() {
		stepResult = new StepResult();
		assertEquals(_UNDEFINED, stepResult.getMoveDirection());
		assertFalse(stepResult.isFire());
	}

	public void testMoveLeft() {
		assertEquals(_UNDEFINED, stepResult.getMoveDirection());
		stepResult.moveLeft();
		assertEquals(LEFT, stepResult.getMoveDirection());
	}

	public void testMoveRight() {
		assertEquals(_UNDEFINED, stepResult.getMoveDirection());
		stepResult.moveRight();
		assertEquals(RIGHT, stepResult.getMoveDirection());
	}

	public void testFire() {
		assertFalse(stepResult.isFire());
		stepResult.fire();
		assertTrue(stepResult.isFire());
	}

	public void testReset() {
		stepResult.moveLeft();
		stepResult.fire();
		stepResult.reset();
		assertEquals(_UNDEFINED, stepResult.getMoveDirection());
		assertFalse(stepResult.isFire());
	}
}
