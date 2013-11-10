
/**
 * Call adjustValue with an integer literal for the value,
 * where the value exceeds the valid range.
 * This is definitely UNSAFE.
 */
public class AdjustValueDirectOverflow {

	public static void adjustValueDirectOverflow() {
		AircraftControl ac = new AircraftControl();
		ac.adjustValue(3, 1000);
	}
	
}
