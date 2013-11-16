package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class TrinaryTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodTrinary1() {
		testAnyProgram("GoodTrinary1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodTrinary1Debug() {
		testAnyProgram("GoodTrinary1", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary2() {
		testAnyProgram("BadTrinary2", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary2Debug() {
		testAnyProgram("BadTrinary2", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary3() {
		testAnyProgram("BadTrinary3", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary3Debug() {
		testAnyProgram("BadTrinary3", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary4() {
		testAnyProgram("BadTrinary4", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary4Debug() {
		testAnyProgram("BadTrinary4", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary5() {
		testAnyProgram("BadTrinary5", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadTrinary5Debug() {
		testAnyProgram("BadTrinary5", UNSAFE, DEBUG);
	}
}
