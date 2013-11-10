package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class ReadSensorTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testReadSensor() {
		testAnyProgram("ReadSensor", true, false);
	}

	@Test
	//@Test(timeout = 10000)
	public void testReadSensorDebug() {
		testAnyProgram("ReadSensor", true, true);
	}

}