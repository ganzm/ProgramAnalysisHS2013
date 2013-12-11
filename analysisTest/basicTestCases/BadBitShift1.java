public class BadBitShift1 {

	public static void main(String[] args) {
		bitShiftTest();
	}

	public static void bitShiftTest() {
		AircraftControl ac = new AircraftControl();

		int i = -1024;

		System.out.println(i + " as binary String " + Integer.toBinaryString(i));
		i = i >> 1;
		i = i << 1;
		ac.adjustValue(2, i);
	}
}
