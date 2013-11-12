package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BranchesAndLoopsTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleIfClause() {
		testAnyProgram("SimpleIfClause", SAFE, false);
	}

	@Test
	public void testSimpleIfClauseDebug() {
		testAnyProgram("SimpleIfClause", SAFE, true);
	}

}
