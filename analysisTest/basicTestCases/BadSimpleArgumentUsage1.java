/**
 * Use an arbitrary argument to adjust {@link AircraftControl}. This is UNSAFE.
 */
public class BadSimpleArgumentUsage1 {

	public static void simpleArgumentUsage(int arg0) {
		AircraftControl ac = new AircraftControl();
		ac.adjustValue(8, arg0);
	}

}
