/**
 * Read a value, take 1100 if equal 100, then assign it. This is UNSAFE.
 */
public class BadSimpleIfEqualClause1 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value == 100) {
			value -= 1100;
		}
		ac.adjustValue(4, value);
	}

}
