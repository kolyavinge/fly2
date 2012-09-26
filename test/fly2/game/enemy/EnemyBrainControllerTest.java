package fly2.game.enemy;

import fly2.game.frontend.PlaneKind;
import fly2.unittest.stub.TestEnemyBrainContext;
import fly2.unittest.stub.TestPlane;
import fly2.unittest.stub.TestPlaneActions;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class EnemyBrainControllerTest extends TestCase {

	private EnemyBrainController controller;
	private TestPlaneActions actions;

	public void setUp() {
		actions = new TestPlaneActions();
		Collection<EnemyBrainContext> contextCollection = getContextCollection();
		controller = new EnemyBrainController(brainContainerStub, contextCollection);
	}

	public void testActivate() {
		controller.activate();
		assertTrue(actions.moveRightFlag);
		assertTrue(actions.fireFlag);
	}

	public void testSetNullBrainContainer() {
		try {
			new EnemyBrainController(null, new ArrayList<EnemyBrainContext>());
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullContextCollection() {
		try {
			new EnemyBrainController(brainContainerStub, null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private Collection<EnemyBrainContext> getContextCollection() {
		TestEnemyBrainContext context = new TestEnemyBrainContext();
		context.setEnemy(new TestPlane());
		context.setEnemyActions(actions);

		Collection<EnemyBrainContext> contextCollection = new ArrayList<EnemyBrainContext>();
		contextCollection.add(context);

		return contextCollection;
	}

	/* Заглушка для EnemyBrainContainer */
	private final EnemyBrainContainer brainContainerStub = new EnemyBrainContainer() {
		public EnemyBrain getBrain(PlaneKind planeKind) {

			return new EnemyBrain() {

				private EnemyBrainContext context;

				public void setContext(EnemyBrainContext context) {
					this.context = context;
				}

				public void activate() {
					context.getEnemyActions().moveRight();
					context.getEnemyActions().fire();
				}
			};
		}
	};
}
