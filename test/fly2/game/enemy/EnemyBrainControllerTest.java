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
		controller = new EnemyBrainController(brainContainerStub);
	}

	public void testActivateForAlivePlane() {
		TestPlane plane = new TestPlane();
		plane.setHealth(10);
		controller.setContextCollection(getContextCollection(plane));
		controller.activate();
		assertTrue(actions.moveRightFlag);
		assertTrue(actions.fireFlag);
	}
	
	public void testActivateForDestroyPlane() {
		TestPlane plane = new TestPlane();
		plane.setHealth(0);
		controller.setContextCollection(getContextCollection(plane));
		controller.activate();
		assertTrue(actions.noMoveFlag);
		assertFalse(actions.fireFlag);
	}

	public void testSetNullBrainContainer() {
		try {
			new EnemyBrainController(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullContextCollection() {
		try {
			controller.setContextCollection(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private Collection<EnemyBrainContext> getContextCollection(TestPlane plane) {
		TestEnemyBrainContext context = new TestEnemyBrainContext();
		context.setEnemy(plane);
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
