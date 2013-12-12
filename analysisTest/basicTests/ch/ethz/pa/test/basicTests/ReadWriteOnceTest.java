package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ReadWriteOnceTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testReadWriteSimple() {
		testAnyProgram("GoodReadWriteSimple", SAFE);
	}

	@Test(timeout = 10000)
	public void testWrongWriteTwice() {
		testAnyProgram("BadWriteTwice", UNSAFE);
	}

	@Test
	public void testWrongReadeTwice() {
		testAnyProgram("BadReadTwice", UNSAFE);
	}

	@Test
	public void testWrongReadeBelow() {
		testAnyProgram("BadReadBelow", UNSAFE);
	}

	@Test
	public void testWrongReadeAbove() {
		testAnyProgram("BadReadAbove", UNSAFE);
	}

	@Test
	public void testBadAdjustBelow() {
		testAnyProgram("BadAdjustBelow", UNSAFE);
	}

	@Test
	public void testBadAdjustAbove() {
		testAnyProgram("BadAdjustAbove", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodReadTwice() {
		testAnyProgram("GoodReadTwice", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadAliasedDoubleRead() {
		testAnyProgram("BadAliasedDoubleRead", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodAliasedDoubleRead() {
		testAnyProgram("GoodAliasedDoubleRead", SAFE);
	}

}
