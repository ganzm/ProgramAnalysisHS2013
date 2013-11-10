
/**
 * Call adjustValue with an integer literal for the value,
 * but on {@link PowerPlantControl} instead of {@link AircraftControl}.
 * This is probably SAFE.
 */
public class AdjustValueDirectOverflow {

	public static void adjustValueDirectOverflow() {
		AircraftControl ac = new AircraftControl();
		ac.adjustValue(3, 1000);
	}
	
}
