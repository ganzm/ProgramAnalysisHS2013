/**
 * Take a reading and make sure it is below 0, then compute the remainder of 999 versus the reading,
 * and assign. This is SAFE.
 */
public class GoodRemainderByZero3 {
	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();

		int v1 = ac.readSensor(5);
		v1 = v1 < 0 ? v1 : -1;
		int v2 = 999 % v1;

		ac.adjustValue(8, v2);
	}
}
