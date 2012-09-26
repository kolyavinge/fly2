package fly2.game.view;

import fly2.game.view.Camera;
import fly2.unittest.stub.TestCameraObservableObject;
import junit.framework.TestCase;

public class CameraTest extends TestCase {

	private Camera camera;
	private TestCameraObservableObject observeObject;
	private double worldWidth = 100.0;
	private double cameraWidth = 20.0;

	public void setUp() {
		camera = new Camera();
		camera.setWorldWidth(worldWidth);
		camera.setCameraWidth(cameraWidth);
		observeObject = new TestCameraObservableObject();
		camera.setObservableObject(observeObject);
	}

	public void testNew() {
		camera = new Camera();
		assertEquals(0, camera.getWorldWidth(), 0.001);
		assertEquals(0, camera.getCameraWidth(), 0.001);
		assertEquals(0, camera.getLookX(), 0.001);
		assertEquals(0, camera.getLookY(), 0.001);
		assertNull(camera.getObservableObject());
	}

	public void testGetSet() {
		CameraObservableObject cameraObject = new TestCameraObservableObject();
		camera = new Camera();
		camera.setWorldWidth(50.0);
		camera.setCameraWidth(12.0);
		camera.setObservableObject(cameraObject);
		assertEquals(50.0, camera.getWorldWidth(), 0.001);
		assertEquals(12.0, camera.getCameraWidth(), 0.001);
		assertSame(cameraObject, camera.getObservableObject());
	}

	public void testCenter() {
		observeObject.setWidth(2.0).setHeight(4.0).setX(10.0).setY(20.0);
		camera.center();
		assertLook(11.0, 22.0);
	}

	public void testLook() {
		camera.setLook(1.0, 1.0);
		camera.setCameraWidth(2.0);
		observeObject.setWidth(2.0).setHeight(2.0).setX(3.0).setY(1.0);
		camera.look();
		assertLook(3.0, 2.0);
	}

	public void testLook2() {
		camera.setLook(3.0, 1.0);
		camera.setCameraWidth(2.0);
		observeObject.setWidth(2.0).setHeight(2.0).setX(1.0).setY(1.0);
		camera.look();
		assertLook(1.0, 2.0);
	}

	public void testLook3() {
		camera.setLook(3.0, 1.0);
		camera.setWorldWidth(10.0);
		camera.setCameraWidth(4.0);
		observeObject.setWidth(1.0).setHeight(2.0).setX(9.0).setY(1.0);
		camera.look();
		assertLook(8.0, 2.0);
	}

	public void testLook4() {
		camera.setLook(3.0, 1.0);
		camera.setCameraWidth(2.0);
		observeObject.setWidth(2.0).setHeight(2.0).setX(0).setY(1.0);
		camera.look();
		assertLook(1.0, 2.0);
	}

	public void testNeativeWorldWidth() {
		try {
			camera.setWorldWidth(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testNeativeCameraWidth() {
		try {
			camera.setCameraWidth(-1);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testNullObservableObject() {
		try {
			camera.setObservableObject(null);
			fail();
		} catch (NullPointerException exp) {
		}
	}

	private void assertLook(double x, double y) {
		assertEquals("x", x, camera.getLookX());
		assertEquals("y", y, camera.getLookY());
	}
}
