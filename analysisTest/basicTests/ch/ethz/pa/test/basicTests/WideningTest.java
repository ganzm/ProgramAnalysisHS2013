package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WideningTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadWidening1() {
		testAnyProgram("BadWidening1", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodWidening2() {
		testAnyProgram("GoodWidening2", SAFE);
	}
}
