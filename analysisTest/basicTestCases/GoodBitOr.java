/**
 * Take a reading and do some safe xor-based bit-toggling on some lower bits. This is SAFE.
 */
public class GoodBitOr {

	public static void bitXorTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		v1 |= 1;
		ac.adjustValue(8, v1);
	}

}
