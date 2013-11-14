package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ArgumentTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadSimpleArgumentUsage1() {
		testAnyProgram("BadSimpleArgumentUsage1", UNSAFE, NO_DEBUG);
	}

	@Test
	public void testBadSimpleArgumentUsage1Debug() {
		testAnyProgram("BadSimpleArgumentUsage1", UNSAFE, DEBUG);
	}
}
