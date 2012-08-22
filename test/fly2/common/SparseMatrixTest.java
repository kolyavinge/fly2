package fly2.common;

import java.util.NoSuchElementException;
import junit.framework.TestCase;

public class SparseMatrixTest extends TestCase {

	private SparseMatrix<String, Double, Character> matrix;

	public void setUp() {
		matrix = new SparseMatrix<String, Double, Character>();
	}

	public void testAddAndGet() {
		String rowKey = "string string string";
		Double colKey = 123456.789;
		Character value = 'A';
		matrix.add(rowKey, colKey, value);
		Character actual = matrix.get(rowKey, colKey);
		assertEquals(value, actual);
	}

	public void testGetError() {
		try {
			matrix.get("string", 123.456);
			fail();
		} catch (NoSuchElementException exp) {
		}
	}

	public void testAddAndGet2() {
		String rowKey1 = "string string string";
		Double colKey1 = 123456.789;
		Character value1 = 'A';
		matrix.add(rowKey1, colKey1, value1);

		String rowKey2 = "string string string";
		Double colKey2 = 789456.123;
		Character value2 = 'B';
		matrix.add(rowKey2, colKey2, value2);

		String rowKey3 = "abcdef";
		Double colKey3 = 789456.123;
		Character value3 = 'C';
		matrix.add(rowKey3, colKey3, value3);

		assertEquals('A', (char) matrix.get(rowKey1, colKey1));
		assertEquals('B', (char) matrix.get(rowKey2, colKey2));
		assertEquals('C', (char) matrix.get(rowKey3, colKey3));
	}

	public void testContains() {
		String rowKey = "string string string";
		Double colKey = 123456.789;
		matrix.add(rowKey, colKey, 'A');
		assertTrue(matrix.contains(rowKey, colKey));
	}

	public void testNotContains() {
		String rowKey = "string string string";
		Double colKey = 123456.789;
		assertFalse(matrix.contains(rowKey, colKey));
	}

	public void testAddDuplicateKey() {
		String rowKey = "string string string";
		Double colKey = 123456.789;
		matrix.add(rowKey, colKey, 'A');
		try {
			matrix.add(rowKey, colKey, 'B');
			fail();
		} catch (DuplicateKeyException exp) {
		}
	}

	public void testRemoveNotExistsKey() {
		String rowKey = "string string string";
		Double colKey = 123456.789;
		try {
			matrix.remove(rowKey, colKey);
			fail();
		} catch (NoSuchElementException exp) {
		}
	}
}
