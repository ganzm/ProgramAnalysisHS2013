
/**
 * Call adjustValue with an integer literal for the value,
 * but on {@link PowerPlantControl} instead of {@link AircraftControl}.
 * This is probably SAFE.
 */
public class AdjustValueDirectOverflow {

	public static void adjustValueDirectOverflow() {
		PowerPlantControl ppc = new PowerPlantControl();
		ppc.adjustValue(3, 1000);
	}
	
}
