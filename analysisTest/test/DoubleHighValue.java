
/**
 * Doubles 499, then assigns it.
 * This is SAFE.
 */
public class DoubleHighValue {

	public static void doubleHighValue() {
		AircraftControl ac = new AircraftControl();
		int value = 499;
		value = value *2;
		ac.adjustValue(8, value);
	}
	
}
