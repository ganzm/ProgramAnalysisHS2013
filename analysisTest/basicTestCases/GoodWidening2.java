/**
 * Increment an unrelated sensor value for undertermined many times. Since that value is not used,
 * this is SAFE.
 */
public class GoodWidening2 {

	public static void doWidening() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(4);
		int v2 = ac.readSensor(5);
		while (v1 > 0) {
			--v1;
			++v2;
		}
		if (v2 > 0) {
			++v1;
		}
		ac.adjustValue(6, v1);
	}

}
