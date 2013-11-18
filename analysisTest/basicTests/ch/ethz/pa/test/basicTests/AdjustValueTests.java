package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class AdjustValueTests extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testAdjustValueDirect() {
		testAnyProgram("GoodAdjustValueDirect", SAFE);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirect() {
		testAnyProgram("GoodAdjustValueIndirect", SAFE);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflow() {
		testAnyProgram("GoodAdjustValueDirectOverflow", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValue() {
		testAnyProgram("GoodAdjustUnrelatedValue", SAFE);
	}
}
