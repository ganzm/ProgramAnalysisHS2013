
/**
 * Invert a sensor value, then assign it.
 * This is SAFE.
 */
public class InvertSensorValue {

	public static void invertSensorValue() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(15);
		int v2 = -v1;
		ac.adjustValue(0, v2);
	}
	
}
