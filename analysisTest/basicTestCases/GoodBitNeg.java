/**
 * Take a reading and do some safe xor-based bit-toggling on some lower bits. This is SAFE.
 */
public class GoodBitNeg {

	public static void bitNegTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		if (v1 >= -512 && v1 < 511) {
			v1 = ~v1;
		}
		ac.adjustValue(8, v1);
	}

}
