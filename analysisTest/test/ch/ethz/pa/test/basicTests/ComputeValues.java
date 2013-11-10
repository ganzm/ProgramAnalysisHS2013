package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ComputeValues extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testIncrementValue() {
		testAnyProgram("IncrementValue", false, false);
	}

	@Test(timeout = 10000)
	public void testIncrementValueDebug() {
		testAnyProgram("IncrementValue", false, true);
	}

}
