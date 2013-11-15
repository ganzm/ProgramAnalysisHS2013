package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ComputeValues extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testIncrementValue() {
		testAnyProgram("IncrementValue", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testIncrementValueDebug() {
		testAnyProgram("IncrementValue", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testIncrementLargeValue() {
		testAnyProgram("IncrementLargeValue", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testIncrementLargeValueDebug() {
		testAnyProgram("IncrementLargeValue", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testDecrementLargeValue() {
		testAnyProgram("DecrementLargeValue", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testDecrementLargeValueDebug() {
		testAnyProgram("DecrementLargeValue", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testIncrementHighValue() {
		testAnyProgram("IncrementHighValue", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testIncrementHighValueDebug() {
		testAnyProgram("IncrementHighValue", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testDoubleHighValue() {
		testAnyProgram("DoubleHighValue", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testDoubleHighValueDebug() {
		testAnyProgram("DoubleHighValue", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testDoubleLargeValue() {
		testAnyProgram("DoubleLargeValue", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testDoubleLargeValueDebug() {
		testAnyProgram("DoubleLargeValue", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testHalfSensorValue() {
		testAnyProgram("HalfSensorValue", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testHalfSensorValueDebug() {
		testAnyProgram("HalfSensorValue", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValues() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValuesDebug() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testInvertSensorValue() {
		testAnyProgram("InvertSensorValue", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testInvertSensorValueDebug() {
		testAnyProgram("InvertSensorValue", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSubtractFromMinusOne() {
		testAnyProgram("SubtractFromMinusOne", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSubtractFromMinusOneDebug() {
		testAnyProgram("SubtractFromMinusOne", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleRemainder1() {
		testAnyProgram("GoodSimpleRemainder1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleRemainder1Debug() {
		testAnyProgram("GoodSimpleRemainder1", SAFE, DEBUG);
	}

}
