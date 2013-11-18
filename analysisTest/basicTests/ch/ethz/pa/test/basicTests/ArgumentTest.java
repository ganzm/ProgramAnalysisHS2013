package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ArgumentTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testBadSimpleArgumentUsage1() {
		testAnyProgram("BadSimpleArgumentUsage1", UNSAFE);
	}
}
