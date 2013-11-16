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

}
