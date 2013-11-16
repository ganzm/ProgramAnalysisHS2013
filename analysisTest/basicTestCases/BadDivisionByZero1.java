/**
 * Divide 100 by a reading, limit the result and assign. This is UNSAFE.
 */
public class BadDivisionByZero1 {
	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();

		int v1 = ac.readSensor(5);
		int v2 = 100 / v1;

		v2 = v2 < -999 ? -999 : v2;
		v2 = v2 > 999 ? 999 : v2;
		ac.adjustValue(8, v2);
	}
}
