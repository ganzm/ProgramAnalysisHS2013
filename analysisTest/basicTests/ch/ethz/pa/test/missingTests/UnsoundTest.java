package ch.ethz.pa.test.missingTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class UnsoundTest extends ValidationTestBase {

	@Test
	public void testTestSimpleGen1_225_at_testL_AboveEnd_1() {
		testAnyProgram("TestSimpleGen1_225_at_testL_AboveEnd_1", UNSAFE);
	}

	@Test
	public void testTestSimpleGen1_226_at_testL_Negative_2() {
		testAnyProgram("TestSimpleGen1_226_at_testL_Negative_2", UNSAFE);
	}

	@Test
	public void testTestSimpleGen13_891_at_testDMD_AboveEnd_21() {
		testAnyProgram("TestSimpleGen13_891_at_testDMD_AboveEnd_21", UNSAFE);
	}

	@Test
	public void testTestSimpleGen13_892_at_testDMD_Negative_22() {
		testAnyProgram("TestSimpleGen13_892_at_testDMD_Negative_22", UNSAFE);
	}

	@Test
	public void testTestSimpleGen13_893_at_testDMD_NegativeAboveEnd_23() {
		testAnyProgram("TestSimpleGen13_893_at_testDMD_NegativeAboveEnd_23", UNSAFE);
	}

	@Test
	public void testTestSimpleGen13_928_at_testRR_Negative_58() {
		testAnyProgram("TestSimpleGen13_928_at_testRR_Negative_58", UNSAFE);
	}

	@Test
	public void testTestSimpleGen14_286_at_testRR_Negative_58() {
		testAnyProgram("TestSimpleGen14_286_at_testRR_Negative_58", UNSAFE);
	}

}
