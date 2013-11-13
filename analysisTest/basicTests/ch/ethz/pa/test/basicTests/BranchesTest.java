package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause1() {
		testAnyProgram("GoodSimpleIfClause1", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1Debug() {
		testAnyProgram("GoodSimpleIfClause1", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1inv() {
		testAnyProgram("GoodSimpleIfClause1inv", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1invDebug() {
		testAnyProgram("GoodSimpleIfClause1inv", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2() {
		testAnyProgram("BadSimpleIfClause2", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2Debug() {
		testAnyProgram("BadSimpleIfClause2", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2inv() {
		testAnyProgram("BadSimpleIfClause2inv", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2invDebug() {
		testAnyProgram("BadSimpleIfClause2inv", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause3() {
		testAnyProgram("GoodSimpleIfClause3", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause3Debug() {
		testAnyProgram("GoodSimpleIfClause3", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause1() {
		testAnyProgram("GoodSimpleIfEqualClause1", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause1Debug() {
		testAnyProgram("GoodSimpleIfEqualClause1", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause2() {
		testAnyProgram("GoodSimpleIfEqualClause2", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause2Debug() {
		testAnyProgram("GoodSimpleIfEqualClause2", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause1() {
		testAnyProgram("BadSimpleIfEqualClause1", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause1Debug() {
		testAnyProgram("BadSimpleIfEqualClause1", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause2() {
		testAnyProgram("BadSimpleIfEqualClause2", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause2Debug() {
		testAnyProgram("BadSimpleIfEqualClause2", UNSAFE, true);
	}
}
