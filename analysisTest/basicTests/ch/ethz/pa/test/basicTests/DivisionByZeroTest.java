package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class DivisionByZeroTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadDivisionByZero1() {
		testAnyProgram("BadDivisionByZero1", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadDivisionByZero1Debug() {
		testAnyProgram("BadDivisionByZero1", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero2() {
		testAnyProgram("GoodDivisionByZero2", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero2Debug() {
		testAnyProgram("GoodDivisionByZero2", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero3() {
		testAnyProgram("GoodDivisionByZero3", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodDivisionByZero3Debug() {
		testAnyProgram("GoodDivisionByZero3", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadRemainderByZero1() {
		testAnyProgram("BadRemainderByZero1", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadRemainderByZero1Debug() {
		testAnyProgram("BadRemainderByZero1", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero2() {
		testAnyProgram("GoodRemainderByZero2", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero2Debug() {
		testAnyProgram("GoodRemainderByZero2", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero3() {
		testAnyProgram("GoodRemainderByZero3", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodRemainderByZero3Debug() {
		testAnyProgram("GoodRemainderByZero3", SAFE, DEBUG);
	}
}
