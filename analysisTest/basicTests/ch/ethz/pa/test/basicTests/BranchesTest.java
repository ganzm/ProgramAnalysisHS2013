package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause1() {
		testAnyProgram("GoodSimpleIfClause1", SAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1inv() {
		testAnyProgram("GoodSimpleIfClause1inv", SAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2() {
		testAnyProgram("BadSimpleIfClause2", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2inv() {
		testAnyProgram("BadSimpleIfClause2inv", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause3() {
		testAnyProgram("GoodSimpleIfClause3", SAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause1() {
		testAnyProgram("GoodSimpleIfEqualClause1", SAFE);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause2() {
		testAnyProgram("GoodSimpleIfEqualClause2", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause1() {
		testAnyProgram("BadSimpleIfEqualClause1", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause2() {
		testAnyProgram("BadSimpleIfEqualClause2", UNSAFE);
	}
}
