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
}
