package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class AdjustValueTests extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testAdjustValueDirect() {
		testAnyProgram("GoodAdjustValueDirect", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectDebug() {
		testAnyProgram("GoodAdjustValueDirect", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirect() {
		testAnyProgram("GoodAdjustValueIndirect", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirectDebug() {
		testAnyProgram("GoodAdjustValueIndirect", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflow() {
		testAnyProgram("GoodAdjustValueDirectOverflow", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflowDebug() {
		testAnyProgram("GoodAdjustValueDirectOverflow", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValue() {
		testAnyProgram("GoodAdjustUnrelatedValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValueDebug() {
		testAnyProgram("GoodAdjustUnrelatedValue", SAFE, true);
	}

}
