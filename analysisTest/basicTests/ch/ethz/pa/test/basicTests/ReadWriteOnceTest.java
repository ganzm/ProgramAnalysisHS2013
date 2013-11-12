package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ReadWriteOnceTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testReadWriteSimple() {
		testAnyProgram("ReadWriteSimple", SAFE);
	}

	@Test(timeout = 10000)
	public void testWrongWriteTwice() {
		testAnyProgram("WrongWriteTwice", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testWrongReadeTwice() {
		testAnyProgram("WrongReadTwice", UNSAFE);
	}

}
