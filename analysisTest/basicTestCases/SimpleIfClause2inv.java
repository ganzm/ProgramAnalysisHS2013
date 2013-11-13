
/**
 * Read a value, take 1101 if above 100, then assigns it.
 * This is UNSAFE.
 */
public class SimpleIfClause2inv {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (100 < value) {
			value -= 1101;
		};
		ac.adjustValue(4, value);
	}
	
}
