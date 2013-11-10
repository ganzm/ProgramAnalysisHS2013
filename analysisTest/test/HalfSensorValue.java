
/**
 * Halves a value, then assigns it.
 * This is SAFE.
 */
public class HalfSensorValue {

	public static void halfSensorValue() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(6);
		value = value / 2;
		ac.adjustValue(8, value);
	}
	
}
