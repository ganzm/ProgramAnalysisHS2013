package ch.ethz.pa.test.missingTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ImpreciseTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testTestSimpleGen10_187_at_testRRR_OK_12() {
		testAnyProgram("TestSimpleGen10_187_at_testRRR_OK_12", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen10_199_at_testRRR_OK_24() {
		testAnyProgram("TestSimpleGen10_199_at_testRRR_OK_24", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen11_380_at_testOAMu_OK_8() {
		testAnyProgram("TestSimpleGen11_380_at_testOAMu_OK_8", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen11_384_at_testOMuM_OK_12() {
		testAnyProgram("TestSimpleGen11_384_at_testOMuM_OK_12", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen11_424_at_testOSMSSuuS_OK_52() {
		testAnyProgram("TestSimpleGen11_424_at_testOSMSSuuS_OK_52", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen11_404_at_testOASSuAOA_OK_32() {
		testAnyProgram("TestSimpleGen11_404_at_testOASSuAOA_OK_32", SAFE);
	}

	@Test(timeout = 10000)
	public void testTestSimpleGen11_416_at_testOSSuSS_OK_44() {
		testAnyProgram("TestSimpleGen11_416_at_testOSSuSS_OK_44", SAFE);
	}

}
