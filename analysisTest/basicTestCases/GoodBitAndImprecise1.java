/**
 * Take two readings, bit-and them if both positive (otherwise 0), then assign. This is SAFE.
 */
public class GoodBitAndImprecise1 {

	public static void bitAndTest() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(5);
		int v2 = ac.readSensor(8);
		int v3 = v1;
		if (v1 >= 0 && v2 >= 0) {
			// unfortunatelly we are not precise enough, so we need an additional check to keep
			// below 512
			if (v1 < 512 && v2 < 512) {
				// yes, we have to use '&&', because '||' is unfolded in an inconvenient manner
				// so Jimple is already adding over-approximation that we cannot compensate
				v3 = v1 & v2;
			}
		}
		ac.adjustValue(8, v3);
	}

}
