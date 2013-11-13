/**
 * Read a value, take 1099 if equal 100, then assign it. This is SAFE.
 */
public class GoodSimpleIfEqualClause1 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value == 100) {
			value -= 1099;
		}
		ac.adjustValue(4, value);
	}

}
