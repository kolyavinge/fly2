package fly2.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public final class BufferBuilder {

	private BufferBuilder() {
	}

	public static FloatBuffer asFloat(float[] array) {
		verifyArray(array);
		
		ByteBuffer bb = ByteBuffer.allocateDirect(Float.SIZE * array.length);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(array);
		fb.position(0);

		return fb;
	}
	
	private static void verifyArray(float[] array) {
		if (array == null)
			throw new NullPointerException("array");
	}
}
