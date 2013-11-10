
/**
 * Increments 990 by 9, then assign it.
 * This is UNSAFE.
 */
public class IncrementHighValue {

	public static void incrementHighValue() {
		AircraftControl ac = new AircraftControl();
		int value = 990;
		value = value + 9;
		ac.adjustValue(8, value);
	}
	
}
