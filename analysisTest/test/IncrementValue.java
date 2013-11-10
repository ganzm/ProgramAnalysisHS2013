
/**
 * Read a value, increment it, then assign it.
 * This can be UNSAFE.
 */
public class IncrementValue {

	public static void readSensor() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(5);
		value = value + 1;
		ac.adjustValue(8, value);
	}
	
}
