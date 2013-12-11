public class GoodArrays {

	public static void doStaticStuff() {
		AircraftControl ac1 = new AircraftControl();
		AircraftControl ac2 = ac1;

		String[] stringArray = new String[] { "Hello", "World" };

		stringArray[0] = "Hi";
		String s = stringArray[1];

		System.out.println(s);

		ac1.readSensor(0);
		ac2.adjustValue(0, 0);

	}

	public void doRead() {
		AircraftControl ac1 = new AircraftControl();

		AircraftControl[] acArray = new AircraftControl[2];
		acArray[0] = ac1;
		acArray[1] = acArray[0];

		acArray[0].readSensor(0);
		acArray[0].adjustValue(0, 0);
	}
}
