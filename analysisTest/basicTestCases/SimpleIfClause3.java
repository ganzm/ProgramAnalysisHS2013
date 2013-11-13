
/**
 * Read a value, add 900 if below or equal 99, then assigns it.
 * This is SAFE.
 */
public class SimpleIfClause3 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value <= 99) {
			value += 900;
		};
		ac.adjustValue(4, value);
	}
	
}
