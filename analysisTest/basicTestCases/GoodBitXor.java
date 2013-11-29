/**
 * Take two readings, bit-and them if both positive (otherwise 0), then assign. This is SAFE.
 */
public class GoodBitXor {

	public static void bitXorTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5) ^ 35;
		ac.adjustValue(8, v1);
	}

}
