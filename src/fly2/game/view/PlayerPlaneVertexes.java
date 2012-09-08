package fly2.game.view;

public final class PlayerPlaneVertexes {

	private PlayerPlaneVertexes() {
	}

	public static float[] getVertexes2d() {
		if (vertexes == null)
			createFullVertexes();

		return vertexes;
	}

	private static void createFullVertexes() {
		vertexes = new float[2 * rightVertexes.length];
		normalize(rightVertexes);
		System.arraycopy(rightVertexes, 0, vertexes, 0, rightVertexes.length);
		flipByX(rightVertexes);
		System.arraycopy(rightVertexes, 0, vertexes, rightVertexes.length, rightVertexes.length);
	}

	private static void normalize(float[] v) {
		for (int i = 0; i < v.length - 1; i += 2) {
			v[i] /= width;
			v[i] += 0.5f;
			v[i + 1] /= height;
		}
	}

	private static void flipByX(float[] v) {
		for (int i = 0; i < v.length - 1; i += 2) {
			v[i] = 1f - v[i];
		}

		for (int i = 0; i < v.length - 1; i += 6) {
			float t = v[i + 2];
			float k = v[i + 3];
			v[i + 2] = v[i + 4];
			v[i + 3] = v[i + 5];
			v[i + 4] = t;
			v[i + 5] = k;
		}
	}

	private static float width = 2f * 772f;

	private static float height = 1346f;

	private static float[] vertexes;

	private static final float[] rightVertexes = {

			// нос

			0f, 0f,
			13f, 3f,
			31f, 31f,

			0f, 0f,
			31f, 31f,
			47f, 64f,

			0f, 0f,
			47f, 64f,
			58f, 105f,

			0f, 0f,
			58f, 105f,
			63f, 165f,

			0f, 0f,
			63f, 165f,
			68f, 304f,

			0f, 0f,
			68f, 304f,
			66f, 400f,

			0f, 0f,
			66f, 400f,
			0f, 400f,

			// крыло

			0f, 400f,
			66f, 400f,
			704, 445f,

			0f, 400f,
			704, 445f,
			742f, 464f,

			0f, 400f,
			742f, 464f,
			763f, 485f,

			0f, 400f,
			763f, 485f,
			769f, 524f,

			0f, 400f,
			769f, 524f,
			751f, 584f,

			0f, 400f,
			751f, 584f,
			700f, 654f,

			0f, 400f,
			700f, 654f,
			663f, 660f,

			0f, 400f,
			663f, 660f,
			122f, 767f,

			0f, 400f,
			122f, 767f,
			0f, 767f,

			// хернюшка

			0f, 767f,
			122f, 767f,
			94f, 779f,

			0f, 767f,
			94f, 779f,
			79f, 804f,

			0f, 767f,
			79f, 804f,
			60f, 857f,

			0f, 767f,
			60f, 857f,
			41f, 1092f,

			0f, 767f,
			41f, 1092f,
			0f, 1092f,

			// маленькое крыло

			0f, 1185f,
			0f, 1092f,
			41f, 1092f,

			0f, 1185f,
			41f, 1092f,
			283f, 1170f,

			0f, 1185f,
			283f, 1170f,
			302f, 1185f,

			0f, 1185f,
			302f, 1185f,
			313f, 1224f,

			0f, 1185f,
			313f, 1224f,
			303f, 1256f,

			0f, 1185f,
			303f, 1256f,
			263f, 1283f,

			0f, 1185f,
			263f, 1283f,
			55f, 1289f,

			0f, 1185f,
			55f, 1289f,
			19f, 1235f,

			0f, 1185f,
			19f, 1235f,
			0f, 1346f,
	};
}
