/**
 * Have a conditional with a fixed entry condition. This is SAFE.
 */
public class GoodSimpleBooleanCondition1 {

	public static void simpleBooleanCondition() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(3);
		int v2 = ac.readSensor(5);
		if (v1 > 5 && v2 < 10) {
			v1 -= 5;
			v2 += 5;
		}
		ac.adjustValue(16, 9999);
	}

}
