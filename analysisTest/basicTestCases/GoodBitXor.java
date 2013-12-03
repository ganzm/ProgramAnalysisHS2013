/**
 * Take a reading and do some safe xor-based bit-toggling on some lower bits. This is SAFE.
 */
public class GoodBitXor {

	public static void bitXorTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		if (v1 >= 0) {
			v1 ^= 7;
		} else if (v1 >= -993) {
			v1 ^= 3;
		}
		ac.adjustValue(8, v1);
	}
}
