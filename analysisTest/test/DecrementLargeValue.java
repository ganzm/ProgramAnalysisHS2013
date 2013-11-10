
/**
 * Decrements -990 by 10, then assign it.
 * This is UNSAFE.
 */
public class DecrementLargeValue {

	public static void decrementLargeValue() {
		AircraftControl ac = new AircraftControl();
		int value = -990;
		value = value - 10;
		ac.adjustValue(8, value);
	}
	
}
