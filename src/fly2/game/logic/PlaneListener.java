package fly2.game.logic;

public interface PlaneListener {
	
	void onMoveLeft(Plane plane);
	
	void onMoveRight(Plane plane);

	void onFire(Plane plane, Bullet bullet);
}
