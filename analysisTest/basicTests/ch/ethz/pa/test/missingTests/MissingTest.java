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

	@Test(timeout = 10000)
	public void testTestSoundBranch1_315_at_testBranch4_Fixed() {
		testAnyProgram("TestSoundBranch1_315_at_testBranch4_Fixed", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSoundBranch1_316_at_testBranch5_Fixed() {
		testAnyProgram("TestSoundBranch1_316_at_testBranch5_Fixed", SAFE);
	}

	@Test
	public void testTestSimpleGen10_177_at_testROR_Negative_2() {
		testAnyProgram("TestSimpleGen10_177_at_testROR_Negative_2", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_375_at_testu_NegativeAboveEnd_3() {
		testAnyProgram("TestSimpleGen11_375_at_testu_NegativeAboveEnd_3", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_376_at_testM_OK_4() {
		testAnyProgram("TestSimpleGen11_376_at_testM_OK_4", SAFE);
	}

	@Test
	public void testTestSimpleGen11_377_at_testM_AboveEnd_5() {
		testAnyProgram("TestSimpleGen11_377_at_testM_AboveEnd_5", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_378_at_testM_Negative_6() {
		testAnyProgram("TestSimpleGen11_378_at_testM_Negative_6", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_379_at_testM_NegativeAboveEnd_7() {
		testAnyProgram("TestSimpleGen11_379_at_testM_NegativeAboveEnd_7", UNSAFE);
	}

	@Test
	public void testTestSimpleGen11_380_at_testOAMu_OK_8() {
		testAnyProgram("TestSimpleGen11_380_at_testOAMu_OK_8", SAFE);
	}

}
