
/**
 * Multiply two sensor readings, then assign it.
 * This is UNSAFE.
 */
public class MultiplyTwoSensorValues {

	public static void multiplyTwoSensorValues() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(4);
		int v2 = ac.readSensor(6);
		int value = v1 * v2;
		ac.adjustValue(8, value);
	}
	
}
