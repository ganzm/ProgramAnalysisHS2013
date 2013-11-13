
/**
 * Read a value, add 901 if below 100, then assigns it.
 * This is UNSAFE.
 */
public class BadSimpleIfClause2 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value < 100) {
			value += 901;
		};
		ac.adjustValue(4, value);
	}
	
}
