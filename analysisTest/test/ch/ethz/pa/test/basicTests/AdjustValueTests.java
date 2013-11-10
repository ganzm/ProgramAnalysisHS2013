package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class AdjustValueTests extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testAdjustValueDirect() {
		testAnyProgram("AdjustValueDirect", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectDebug() {
		testAnyProgram("AdjustValueDirect", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirect() {
		testAnyProgram("AdjustValueIndirect", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirectDebug() {
		testAnyProgram("AdjustValueIndirect", SAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflow() {
		testAnyProgram("AdjustValueDirectOverflow", UNSAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflowDebug() {
		testAnyProgram("AdjustValueDirectOverflow", UNSAFE, true);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValue() {
		testAnyProgram("AdjustUnrelatedValue", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValueDebug() {
		testAnyProgram("AdjustUnrelatedValue", SAFE, true);
	}

}
