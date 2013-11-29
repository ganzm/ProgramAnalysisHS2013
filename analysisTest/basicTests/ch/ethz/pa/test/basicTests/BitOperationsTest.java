package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class BitOperationsTest extends ValidationTestBase {

	@Test
	public void testGoodBitAndImprecise1() {
		testAnyProgram("GoodBitAndImprecise1", SAFE);
	}

}
