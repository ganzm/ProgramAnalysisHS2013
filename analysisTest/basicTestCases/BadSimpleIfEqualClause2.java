/**
 * Read a value, add 900 if equal 100, then assign it. This is UNSAFE.
 */
public class BadSimpleIfEqualClause2 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value == 100) {
			value += 900;
		}
		ac.adjustValue(4, value);
	}

}
