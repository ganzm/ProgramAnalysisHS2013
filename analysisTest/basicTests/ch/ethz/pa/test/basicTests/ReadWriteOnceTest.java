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

	@Test(timeout = 10000)
	public void testWrongReadeTwice() {
		testAnyProgram("BadReadTwice", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodReadTwice() {
		testAnyProgram("GoodReadTwice", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadAliasedDoubleRead() {
		testAnyProgram("BadAliasedDoubleRead", UNSAFE);
	}

}
