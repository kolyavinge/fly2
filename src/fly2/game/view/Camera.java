package fly2.game.view;

import static java.lang.Math.abs;

public class Camera {

	private double worldWidth;
	private double cameraWidth;
	private double lookX, lookY;
	private CameraObservableObject observ;

	public void look() {
		lookY = observ.getY() + (observ.getHeight() / 2.0);

		double dx = lookX - observ.getX();
		if (abs(dx) >= cameraWidth) {
			lookX -= dx;
			if (lookX - cameraWidth / 2.0 < 0) {
				lookX = cameraWidth / 2.0;
			} else if (lookX + cameraWidth / 2.0 > worldWidth) {
				lookX = worldWidth - cameraWidth / 2.0;
			}
		}
	}

	public void center() {
		lookX = observ.getX() + (observ.getWidth() / 2.0);
		lookY = observ.getY() + (observ.getHeight() / 2.0);
	}

	public double getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(double worldWidth) {
		if (worldWidth < 0.0)
			throw new IllegalArgumentException("worldWidth");

		this.worldWidth = worldWidth;
	}

	public double getCameraWidth() {
		return cameraWidth;
	}

	public void setCameraWidth(double cameraWidth) {
		if (cameraWidth < 0.0)
			throw new IllegalArgumentException("cameraWidth");

		this.cameraWidth = cameraWidth;
	}

	public CameraObservableObject getObservableObject() {
		return observ;
	}

	public void setObservableObject(CameraObservableObject cameraObject) {
		if (cameraObject == null)
			throw new NullPointerException("cameraObject");

		this.observ = cameraObject;
	}

	public double getLookX() {
		return lookX;
	}

	public double getLookY() {
		return lookY;
	}

	void setLook(double x, double y) {
		this.lookX = x;
		this.lookY = y;
	}
}
