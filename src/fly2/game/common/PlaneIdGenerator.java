package fly2.game.common;

public final class PlaneIdGenerator {

	private static final PlaneIdGenerator instance = new PlaneIdGenerator();

	public static PlaneIdGenerator getInstance() {
		return instance;
	}

	private static int lastId = 1;

	private PlaneIdGenerator() {
	}

	public int generate() {
		return lastId++;
	}
}
