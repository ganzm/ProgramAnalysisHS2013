public class GoodArrays {

	private static int arbitraryInteger = -1;

	public static void doStaticStuff() {
		AircraftControl ac1 = new AircraftControl();
		AircraftControl ac2 = ac1;

		String[] stringArray = new String[] { "Hello", "World" };

		ac1.readSensor(0);
		ac2.adjustValue(0, 0);

	}

	public void doRead() {

		AircraftControl ac1 = new AircraftControl();
		AircraftControl ac2 = ac1;

		String[] stringArray = new String[] { "Hello", "World" };

		ac1.readSensor(0);
		ac2.adjustValue(0, 0);
	}
}
