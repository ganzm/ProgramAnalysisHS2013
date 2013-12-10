public class BadBitShift1 {

	public static void bitShiftTest() {
		AircraftControl ac = new AircraftControl();

		int i = -1024;
		i = i >> 1;
		ac.adjustValue(2, i);
	}
}
