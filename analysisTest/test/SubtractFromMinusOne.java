
/**
 * Subtracts sensor from -1, then assign it.
 * This is UNSAFE.
 */
public class SubtractFromMinusOne {

	public static void subtractFromMinusOne() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(2);
		value = -1 - value;
		ac.adjustValue(8, value);
	}
	
}
