package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesAndLoopsTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause1() {
		testAnyProgram("SimpleIfClause1", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1Debug() {
		testAnyProgram("SimpleIfClause1", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1inv() {
		testAnyProgram("SimpleIfClause1inv", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause1invDebug() {
		testAnyProgram("SimpleIfClause1inv", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2() {
		testAnyProgram("SimpleIfClause2", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2Debug() {
		testAnyProgram("SimpleIfClause2", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2inv() {
		testAnyProgram("SimpleIfClause2inv", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testSimpleIfClause2invDebug() {
		testAnyProgram("SimpleIfClause2inv", UNSAFE, true);
	}

}
