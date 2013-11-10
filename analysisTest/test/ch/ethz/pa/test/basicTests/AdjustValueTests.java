package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class AdjustValueTests extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testAdjustValueDirect() {
		testAnyProgram("AdjustValueDirect", true, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectDebug() {
		testAnyProgram("AdjustValueDirect", true, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirect() {
		testAnyProgram("AdjustValueIndirect", true, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirectDebug() {
		testAnyProgram("AdjustValueIndirect", true, true);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflow() {
		testAnyProgram("AdjustValueDirectOverflow", false, false);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflowDebug() {
		testAnyProgram("AdjustValueDirectOverflow", false, true);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValue() {
		testAnyProgram("AdjustUnrelatedValue", true, false);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValueDebug() {
		testAnyProgram("AdjustUnrelatedValue", true, true);
	}

}
