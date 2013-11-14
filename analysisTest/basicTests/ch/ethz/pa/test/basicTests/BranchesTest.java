package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause1() {
		testAnyProgram("GoodSimpleIfClause1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1Debug() {
		testAnyProgram("GoodSimpleIfClause1", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1inv() {
		testAnyProgram("GoodSimpleIfClause1inv", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1invDebug() {
		testAnyProgram("GoodSimpleIfClause1inv", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2() {
		testAnyProgram("BadSimpleIfClause2", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2Debug() {
		testAnyProgram("BadSimpleIfClause2", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2inv() {
		testAnyProgram("BadSimpleIfClause2inv", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2invDebug() {
		testAnyProgram("BadSimpleIfClause2inv", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause3() {
		testAnyProgram("GoodSimpleIfClause3", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause3Debug() {
		testAnyProgram("GoodSimpleIfClause3", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause1() {
		testAnyProgram("GoodSimpleIfEqualClause1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause1Debug() {
		testAnyProgram("GoodSimpleIfEqualClause1", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause2() {
		testAnyProgram("GoodSimpleIfEqualClause2", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleIfEqualClause2Debug() {
		testAnyProgram("GoodSimpleIfEqualClause2", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause1() {
		testAnyProgram("BadSimpleIfEqualClause1", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause1Debug() {
		testAnyProgram("BadSimpleIfEqualClause1", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause2() {
		testAnyProgram("BadSimpleIfEqualClause2", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleIfEqualClause2Debug() {
		testAnyProgram("BadSimpleIfEqualClause2", UNSAFE, DEBUG);
	}
}
