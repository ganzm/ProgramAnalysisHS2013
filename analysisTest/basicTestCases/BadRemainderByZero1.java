/**
 * Take a reading, compute the remainder of 999 by it, then assign. This is UNSAFE.
 */
public class BadRemainderByZero1 {
	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();

		int v1 = ac.readSensor(5);
		int v2 = 999 % v1;

		ac.adjustValue(8, v2);
	}
}
