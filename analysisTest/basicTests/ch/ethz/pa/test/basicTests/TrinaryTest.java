package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class TrinaryTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodTrinary1() {
		testAnyProgram("GoodTrinary1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodTrinary1Debug() {
		testAnyProgram("GoodTrinary1", SAFE, DEBUG);
	}
}
