
/**
 * Call adjustValue with an integer literal for the value,
 * but on {@link PowerPlantControl} instead of {@link AircraftControl}.
 * This is probably SAFE.
 */
public class AdjustUnrelatedValue {

	public static void adjustUnrelatedValue() {
		PowerPlantControl ppc = new PowerPlantControl();
		ppc.adjustValue(3, 1000);
	}
	
}
