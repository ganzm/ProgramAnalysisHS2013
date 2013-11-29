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

}
