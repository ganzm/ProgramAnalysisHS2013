package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BitOperationsTest extends ValidationTestBase {

	@Test
	public void testGoodBitAndImprecise1() {
		testAnyProgram("GoodBitAndImprecise1", SAFE);
	}

	@Test
	public void testGoodBitXor() {
		testAnyProgram("GoodBitXor", SAFE);
	}

	@Test
	public void testGoodBitOr() {
		testAnyProgram("GoodBitOr", SAFE);
	}

	@Test
	public void testGoodBitNeg() {
		testAnyProgram("GoodBitNeg", SAFE);
	}

	@Test
	public void testGoodBitShift() {
		testAnyProgram("GoodBitShift", SAFE);
	}

	@Test
	public void testBadBitShift1() {
		testAnyProgram("BadBitShift1", UNSAFE);
	}

	@Test
	public void testBadBitShift2() {
		testAnyProgram("BadBitShift2", UNSAFE);
	}

}
