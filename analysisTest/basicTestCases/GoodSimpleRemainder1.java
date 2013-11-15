/**
 * Add 999 to a sensor value, compute modulo 1000, then adjust. This is SAFE.
 */
public class GoodSimpleRemainder1 {

	public static void multiplyTwoSensorValues() {
		AircraftControl ac = new AircraftControl();
		int v1 = 999 + ac.readSensor(3);
		int v2 = v1 % 1000;
		ac.adjustValue(8, v2);
	}

}
