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
		controller = new EnemyBrainController();
		controller.setBrainContainer(brainContainerStub);
	}

	public void testActivateForAlivePlane() {
		TestPlane plane = new TestPlane();
		plane.setHealth(10);
		controller.setContextCollection(getContextCollection(plane));
		controller.activate();
		assertTrue(actions.moveRightFlag);
		assertTrue(actions.fireFlag);
		assertFalse(controller.getContextCollection().isEmpty());
	}

	public void testActivateForDestroyPlane() {
		TestPlane plane = new TestPlane();
		plane.setHealth(0); // дохлый бот
		controller.setContextCollection(getContextCollection(plane));
		controller.activate();
		assertTrue(actions.noMoveFlag);
		assertFalse(actions.fireFlag);
		// контексты для дохлых ботов удаляются
		assertTrue(controller.getContextCollection().isEmpty());
	}

	public void testNew() {
		controller = new EnemyBrainController();
		assertNotNull(controller.getBrainContainer());
		assertNotNull(controller.getContextCollection());
		assertTrue(controller.getContextCollection().isEmpty());
	}
	
	public void testSetNullBrainContainer() {
		controller = new EnemyBrainController();
		try {
			controller.setBrainContainer(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testSetNullContextCollection() {
		controller = new EnemyBrainController();
		try {
			controller.setContextCollection(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	public void testWithoutBrainContainerAndContexts() {
		controller = new EnemyBrainController();
		controller.activate();
		// никакие ошибки не генерируются
	}

	public void testWithoutBrainContainer() {
		TestPlane plane = new TestPlane();
		plane.setHealth(10);
		controller = new EnemyBrainController();
		controller.setContextCollection(getContextCollection(plane));
		try {
			controller.activate();
			fail();
		} catch (IllegalStateException exp) {
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
