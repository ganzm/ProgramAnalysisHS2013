package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class TATest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadSimpleArgumentUsage1() {
		testAnyProgram("TestSimpleGen10_178_at_testROR_NegativeAboveEnd_3", SAFE);
	}

}
