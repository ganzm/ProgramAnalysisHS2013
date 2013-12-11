public class GoodBitShift {
	public static void bitShiftTest() {
		AircraftControl ac = new AircraftControl();

		int i = 1024;
		i = i << 1;
		i = i >> 2;
		ac.adjustValue(1, i);

	}
}
