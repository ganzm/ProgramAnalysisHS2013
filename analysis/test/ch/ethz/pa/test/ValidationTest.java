package ch.ethz.pa.test;


import org.junit.Test;


public class ValidationTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testExample() {
		testAnyProgram("ExampleTest", true, true);
	}

}
