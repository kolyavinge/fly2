package fly2.game.enemy;

import junit.framework.TestCase;

public class ScriptManagerTest extends TestCase {

	private ScriptManager builder;
	private TestGlobalObject testObject;

	public void setUp() {
		builder = new ScriptManager();
		testObject = new TestGlobalObject();
		builder.define("testObject", testObject);
	}

	public void testCallPublicMethod() {
		TestGlobalObject testObject = new TestGlobalObject();
		assertFalse(testObject.publicMethodIsTestExecuted());
		builder.define("testObject", testObject);
		builder.evaluate("testObject.publicMethod();");
		assertTrue(testObject.publicMethodIsTestExecuted());
	}

	public void testCallProtectedMethod() {
		try {
			builder.evaluate("testObject.protectedMethod();");
			fail();
		} catch (org.mozilla.javascript.EcmaError exp) {
		}
	}

	public void testCallPrivateMethod() {
		try {
			builder.evaluate("testObject.privateMethod();");
			fail();
		} catch (org.mozilla.javascript.EcmaError exp) {
		}
	}

	public void testAssignmentPublicField() {
		assertEquals(0, testObject.publicField);
		builder.evaluate("testObject.publicField = 789;");
		assertEquals(789, testObject.publicField);
	}
	
	public void testAssignmentPrivateField() {
		try {
			builder.evaluate("testObject.privateField = 789;");
			fail();
		} catch (org.mozilla.javascript.EvaluatorException exp) {
		}
	}

	public void testEvaluateWithIllegalSource() {
		try {
			builder.evaluate("illegal source");
			fail();
		} catch (org.mozilla.javascript.EvaluatorException exp) {
		}
	}

	public class TestGlobalObject {

		private boolean flag = false;

		public int publicField = 0;

		private int privateField = 0;

		public void publicMethod() {
			flag = true;
		}

		public boolean publicMethodIsTestExecuted() {
			return flag;
		}

		private void privateMethod() {
		}

		protected void protectedMethod() {
		}
	}
}
