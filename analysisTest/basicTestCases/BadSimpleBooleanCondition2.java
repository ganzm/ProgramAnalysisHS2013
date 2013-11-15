/**
 * Get two sensor readings. If legal, add 5 to the one and remove 5 from the other. This is SAFE.
 */
public class BadSimpleBooleanCondition2 {

	public static void simpleBooleanCondition() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(3);
		int v2 = ac.readSensor(5);
		if (v1 > -995 && v2 < 995) {
			v1 -= 6;
			v2 += 6;
		}
		ac.adjustValue(1, v1);
		ac.adjustValue(2, v2);
	}

}
