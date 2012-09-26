package fly2.game.enemy;

import fly2.game.frontend.PlaneKind;
import junit.framework.TestCase;

public class NormalEnemyBrainContainerTest extends TestCase {

	private NormalEnemyBrainContainer factory;

	public void setUp() {
		factory = new NormalEnemyBrainContainer();
	}

	public void testMakeBrain() {
		EnemyBrain brain = factory.getBrain(PlaneKind.ENEMY);
		assertTrue(brain instanceof NormalEnemyBrain);
	}
	
	public void testSameBrainInstance() {
		EnemyBrain brain1 = factory.getBrain(PlaneKind.ENEMY);
		EnemyBrain brain2 = factory.getBrain(PlaneKind.ENEMY);
		EnemyBrain brain3 = factory.getBrain(PlaneKind.ENEMY);
		assertSame(brain1, brain2);
		assertSame(brain2, brain3);
		assertSame(brain1, brain3);
	}

	public void testMakeBrainForPlayer() {
		try {
			factory.getBrain(PlaneKind.PLAYER);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
