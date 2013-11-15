package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WideningTest extends ValidationTestBase {

	@Test(timeout = 1000)
	public void testWidening1() {
		testAnyProgram("Widening1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 1000)
	public void testWidening1Debug() {
		testAnyProgram("Widening1", SAFE, DEBUG);
	}

}
