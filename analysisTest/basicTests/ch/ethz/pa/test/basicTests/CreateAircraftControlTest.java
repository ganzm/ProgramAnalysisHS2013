package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class CreateAircraftControlTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testCreateAircraftControl() {
		testAnyProgram("CreateAircraftControl", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testCreateAircraftControlDebug() {
		testAnyProgram("CreateAircraftControl", SAFE, true);
	}

}
