
/**
 * Call adjustValue with an integer literal for the value.
 * This should be SAFE.
 */
public class GoodAdjustValueDirect {

	public static void adjustValueDirect() {
		AircraftControl ac = new AircraftControl();
		ac.adjustValue(3, 123);
	}
	
}
