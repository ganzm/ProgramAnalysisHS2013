/**
 * Take two readings, bit-and them if both positive (otherwise 0), then assign. This is SAFE.
 */
public class GoodBitAnd {

	public static void trinaryTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		int v2 = ac.readSensor(8);
		int v3 = v1;
		if (v1 >= 0 && v2 >= 0) {
			v3 = v1 & v2;
		}
		ac.adjustValue(8, v3);
	}

}
