package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class DivisionByZeroTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadDivisionByZero1() {
		testAnyProgram("BadDivisionByZero1", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero2() {
		testAnyProgram("GoodDivisionByZero2", SAFE);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero3() {
		testAnyProgram("GoodDivisionByZero3", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadRemainderByZero1() {
		testAnyProgram("BadRemainderByZero1", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero2() {
		testAnyProgram("GoodRemainderByZero2", SAFE);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero3() {
		testAnyProgram("GoodRemainderByZero3", SAFE);
	}

}
