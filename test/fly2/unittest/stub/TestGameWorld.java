package fly2.unittest.stub;

import fly2.game.frontend.GameWorld;

public class TestGameWorld implements GameWorld {

	private double width, height;

	public TestGameWorld() {
		
	}
	
	public TestGameWorld(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
