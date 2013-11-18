package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ComputeValues extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testIncrementValue() {
		testAnyProgram("IncrementValue", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testIncrementLargeValue() {
		testAnyProgram("IncrementLargeValue", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testDecrementLargeValue() {
		testAnyProgram("DecrementLargeValue", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testIncrementHighValue() {
		testAnyProgram("IncrementHighValue", SAFE);
	}

	@Test(timeout = 10000)
	public void testDoubleHighValue() {
		testAnyProgram("DoubleHighValue", SAFE);
	}

	@Test(timeout = 10000)
	public void testDoubleLargeValue() {
		testAnyProgram("DoubleLargeValue", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testHalfSensorValue() {
		testAnyProgram("HalfSensorValue", SAFE);
	}

	@Test(timeout = 10000)
	public void testMultiplyTwoSensorValues() {
		testAnyProgram("MultiplyTwoSensorValues", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testInvertSensorValue() {
		testAnyProgram("InvertSensorValue", SAFE);
	}

	@Test(timeout = 10000)
	public void testSubtractFromMinusOne() {
		testAnyProgram("SubtractFromMinusOne", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleRemainder1() {
		testAnyProgram("GoodSimpleRemainder1", SAFE);
	}

}
