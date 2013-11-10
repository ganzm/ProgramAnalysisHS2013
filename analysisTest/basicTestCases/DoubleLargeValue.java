
/**
 * Doubles 500, then assigns it.
 * This is UNSAFE.
 */
public class DoubleLargeValue {

	public static void doubleLargeValue() {
		AircraftControl ac = new AircraftControl();
		int value = 500;
		value = value *2;
		ac.adjustValue(8, value);
	}
	
}
