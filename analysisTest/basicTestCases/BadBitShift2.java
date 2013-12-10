public class BadBitShift2 {

	public static void bitShiftTest() {
		AircraftControl ac = new AircraftControl();

		int i = 512;
		i = i << 1;
		ac.adjustValue(2, i);
	}
}
