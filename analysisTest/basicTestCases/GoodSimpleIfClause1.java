
/**
 * Read a value, add 900 if below 100, then assigns it.
 * This is SAFE.
 */
public class GoodSimpleIfClause1 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value < 100) {
			value += 900;
		};
		ac.adjustValue(4, value);
	}
	
}
