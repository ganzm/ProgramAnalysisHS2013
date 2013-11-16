package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WideningTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadWidening1() {
		testAnyProgram("BadWidening1", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadWidening1Debug() {
		testAnyProgram("BadWidening1", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodWidening2() {
		testAnyProgram("GoodWidening2", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodWidening2Debug() {
		testAnyProgram("GoodWidening2", SAFE, DEBUG);
	}

}
