package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ComputeValues extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testIncrementValue() {
		testAnyProgram("IncrementValue", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testIncrementValueDebug() {
		testAnyProgram("IncrementValue", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testIncrementLargeValue() {
		testAnyProgram("IncrementLargeValue", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testIncrementLargeValueDebug() {
		testAnyProgram("IncrementLargeValue", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testIncrementHighValue() {
		testAnyProgram("IncrementHighValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testIncrementHighValueDebug() {
		testAnyProgram("IncrementHighValue", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testDoubleHighValue() {
		testAnyProgram("DoubleHighValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testDoubleHighValueDebug() {
		testAnyProgram("DoubleHighValue", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testDoubleLargeValue() {
		testAnyProgram("DoubleLargeValue", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testDoubleLargeValueDebug() {
		testAnyProgram("DoubleLargeValue", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValues() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValuesDebug() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, true);
	}

}
