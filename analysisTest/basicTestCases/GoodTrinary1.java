/**
 * Multiply two readings, then limit to safe range and assign. This is SAFE.
 */
public class GoodTrinary1 {

	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		int v2 = ac.readSensor(8);
		int v3 = v1 * v2;
		v3 = v3 <= 999 ? v3 : 999;
		v3 = v3 >= -999 ? v3 : -999;
		ac.adjustValue(8, v3);
	}

}
