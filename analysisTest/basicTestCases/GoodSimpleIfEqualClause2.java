/**
 * Read a value, add 899 if equal 100, then assign it. This is SAFE.
 */
public class GoodSimpleIfEqualClause2 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value == 100) {
			value += 899;
		}
		ac.adjustValue(4, value);
	}

}
