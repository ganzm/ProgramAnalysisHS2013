/**
 * Multiply two readings, then limit to an unsafe range and assign. This is UNSAFE.
 */
public class BadTrinary4 {

	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		int v2 = ac.readSensor(8);
		int v3 = v1 * v2;
		v3 = v3 <= 999 ? v3 : 999;
		v3 = v3 >= -999 ? v3 : -1000;
		ac.adjustValue(8, v3);
	}

}
