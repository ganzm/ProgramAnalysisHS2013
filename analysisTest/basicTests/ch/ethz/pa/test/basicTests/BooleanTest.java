package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BooleanTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodSimpleBooleanCondition1() {
		testAnyProgram("GoodSimpleBooleanCondition1", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadSimpleBooleanCondition2() {
		testAnyProgram("BadSimpleBooleanCondition2", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodUnreachableCode1() {
		testAnyProgram("GoodUnreachableCode1", SAFE);
	}
}
