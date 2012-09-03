package fly2.common;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import junit.framework.TestCase;

public class BufferBuilderTest extends TestCase {

	public void testBuild() {
		float[] expected = new float[] { 1f, 2f, 3f, 4f, 5f };
		
		FloatBuffer fb = BufferBuilder.asFloat(expected);
		
		assertEquals(ByteOrder.nativeOrder(), fb.order());
		assertEquals(0, fb.position());
		
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], fb.get(i));
		}
	}
	
	public void testNullArray() {
		try {
			BufferBuilder.asFloat(null);
			fail();
		} catch (NullPointerException exp){}
	}
}
