
/**
 * Read a value, take 1100 if above 100, then assigns it.
 * This is SAFE.
 */
public class SimpleIfClause1inv {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (100 < value) {
			value -= 1100;
		};
		ac.adjustValue(4, value);
	}
	
}
