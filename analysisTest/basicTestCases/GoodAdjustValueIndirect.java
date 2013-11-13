
/**
 * Call adjustValue with an integer variable for the value.
 * This should be SAFE.
 */
public class GoodAdjustValueIndirect {

	public static void adjustValueIndirect() {
		AircraftControl ac = new AircraftControl();
		int adjustedValue = 123;
		ac.adjustValue(3, adjustedValue);
	}
	
}
