/**
 * Take a reading and make sure it is above 0, then divide 100 by the reading, limit the result and
 * assign. This is SAFE.
 */
public class GoodDivisionByZero2 {
	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();

		int v1 = ac.readSensor(5);
		v1 = v1 > 0 ? v1 : 1;
		int v2 = 100 / v1;

		v2 = v2 < -999 ? -999 : v2;
		v2 = v2 > 999 ? 999 : v2;
		ac.adjustValue(8, v2);
	}
}
