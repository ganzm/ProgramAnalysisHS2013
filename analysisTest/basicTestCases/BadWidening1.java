/**
 * Increment an unrelated sensor value for undertermined many times. This is UNSAFE.
 */
public class BadWidening1 {

	public static void doWidening() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(4);
		int v2 = ac.readSensor(5);
		while (v1 > 0) {
			--v1;
			++v2;
		}
		ac.adjustValue(6, v2);
	}

}
