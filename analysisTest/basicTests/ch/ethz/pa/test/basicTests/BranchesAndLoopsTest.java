package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesAndLoopsTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause1() {
		testAnyProgram("SimpleIfClause1", SAFE, false);
	}

	@Test
	public void testSimpleIfClause1Debug() {
		testAnyProgram("SimpleIfClause1", SAFE, true);
	}

}
