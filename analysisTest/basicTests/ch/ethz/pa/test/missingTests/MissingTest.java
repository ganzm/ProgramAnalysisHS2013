package ch.ethz.pa.test.missingTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class MissingTest extends ValidationTestBase {

	@Test
	public void testTestSimpleGen10_175_at_testROR_OK_0() {
		testAnyProgram("TestSimpleGen10_175_at_testROR_OK_0", SAFE);
	}

	@Test
	public void testTestSimpleGen10_176_at_testROR_AboveEnd_1() {
		testAnyProgram("TestSimpleGen10_176_at_testROR_AboveEnd_1", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_372_at_testu_OK_0() {
		testAnyProgram("TestSimpleGen11_372_at_testu_OK_0", SAFE);
	}

	@Test
	public void testTestSimpleGen11_373_at_testu_AboveEnd_1() {
		testAnyProgram("TestSimpleGen11_373_at_testu_AboveEnd_1", UNSAFE);
	}

	@Test
	public void testTestSoundBranch1_315_at_testBranch4_Fixed() {
		testAnyProgram("TestSoundBranch1_315_at_testBranch4_Fixed", SAFE);
	}

	@Test
	public void testTestSoundBranch1_316_at_testBranch5_Fixed() {
		testAnyProgram("TestSoundBranch1_316_at_testBranch5_Fixed", SAFE);
	}

}
