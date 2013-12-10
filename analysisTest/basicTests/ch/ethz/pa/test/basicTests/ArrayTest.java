package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ArrayTest extends ValidationTestBase {

	@Test
	public void testArray() {
		testAnyProgram("GoodArrays", SAFE);
	}
}
