package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class AdjustValueTests extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testAdjustValueDirect() {
		testAnyProgram("GoodAdjustValueDirect", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectDebug() {
		testAnyProgram("GoodAdjustValueDirect", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirect() {
		testAnyProgram("GoodAdjustValueIndirect", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustValueIndirectDebug() {
		testAnyProgram("GoodAdjustValueIndirect", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflow() {
		testAnyProgram("GoodAdjustValueDirectOverflow", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustValueDirectOverflowDebug() {
		testAnyProgram("GoodAdjustValueDirectOverflow", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValue() {
		testAnyProgram("GoodAdjustUnrelatedValue", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testAdjustUnrelatedValueDebug() {
		testAnyProgram("GoodAdjustUnrelatedValue", SAFE, DEBUG);
	}

}
