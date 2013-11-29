package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BitOperationsTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodTrinary1() {
		testAnyProgram("GoodTrinary1", SAFE);
	}

	@Test(timeout = 10000)
	public void testGoodBitAnd() {
		testAnyProgram("GoodBitAnd", SAFE);
	}

}
