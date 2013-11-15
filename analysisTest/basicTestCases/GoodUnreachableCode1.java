/**
 * If an impossible value is found, make it worse. This is SAFE.
 */
public class GoodUnreachableCode1 {

	public static void checkUnreachableCode() {
		AircraftControl ac = new AircraftControl();
		int value = ac.readSensor(13);
		if (value > 999) {
			// this is never executed
			value += 1100;
		}
		ac.adjustValue(4, value);
	}

}
