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
	public void testDecrementLargeValue() {
		testAnyProgram("DecrementLargeValue", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testDecrementLargeValueDebug() {
		testAnyProgram("DecrementLargeValue", UNSAFE, true);
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
	public void testHalfSensorValue() {
		testAnyProgram("HalfSensorValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testHalfSensorValueDebug() {
		testAnyProgram("HalfSensorValue", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValues() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValuesDebug() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testInvertSensorValue() {
		testAnyProgram("InvertSensorValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testInvertSensorValueDebug() {
		testAnyProgram("InvertSensorValue", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSubtractFromMinusOne() {
		testAnyProgram("SubtractFromMinusOne", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testSubtractFromMinusOneDebug() {
		testAnyProgram("SubtractFromMinusOne", UNSAFE, true);
	}

}
