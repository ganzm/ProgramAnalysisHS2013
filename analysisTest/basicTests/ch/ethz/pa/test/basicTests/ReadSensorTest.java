package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ReadSensorTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testReadSensor() {
		testAnyProgram("ReadSensor", SAFE, false);
	}

	@Test(timeout = 10000)
	public void testReadSensorDebug() {
		testAnyProgram("ReadSensor", SAFE, true);
	}

}
