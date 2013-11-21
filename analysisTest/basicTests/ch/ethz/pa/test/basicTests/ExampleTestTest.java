package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ExampleTestTest extends ValidationTestBase {

	@Test
	public void testBadExample() {
		testAnyProgram("BadExampleTest", UNSAFE);
	}

	@Test
	public void testExample1() {
		testAnyProgram("ExampleTest1", SAFE);
	}

	@Test
	public void testExample2() {
		testAnyProgram("ExampleTest2", SAFE);
	}

	@Test
	public void testExample3() {
		testAnyProgram("ExampleTest3", UNSAFE);
	}

	@Test
	public void testExample4() {
		testAnyProgram("ExampleTest4", SAFE);
	}
}
