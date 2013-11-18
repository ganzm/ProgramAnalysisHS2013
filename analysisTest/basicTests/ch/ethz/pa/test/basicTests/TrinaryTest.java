package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class TrinaryTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodTrinary1() {
		testAnyProgram("GoodTrinary1", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadTrinary2() {
		testAnyProgram("BadTrinary2", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testBadTrinary3() {
		testAnyProgram("BadTrinary3", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testBadTrinary4() {
		testAnyProgram("BadTrinary4", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testBadTrinary5() {
		testAnyProgram("BadTrinary5", UNSAFE);
	}

}
