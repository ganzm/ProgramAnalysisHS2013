package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class DoNothingTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testDoNothing() {
		testAnyProgram("DoNothing", true, false);
	}

	@Test(timeout = 10000)
	public void testDoNothingDebug() {
		testAnyProgram("DoNothing", true, true);
	}

}
