package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BooleanTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodSimpleBooleanCondition1() {
		testAnyProgram("GoodSimpleBooleanCondition1", SAFE, NO_DEBUG);
	}

	@Test
	public void testGoodSimpleBooleanCondition1Debug() {
		testAnyProgram("GoodSimpleBooleanCondition1", SAFE, DEBUG);
	}
}
