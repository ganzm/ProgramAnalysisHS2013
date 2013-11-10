
/**
 * This funny method has the same methods as the {@link AircraftControl},
 * but is not related to it. Therefore, the specific checks of
 * {@link AircraftControl} do not apply to it.
 */
public class PowerPlantControl {
	public native int readSensor(int sensorId);
	public native void adjustValue(int sensorId, int newValue);

	public PowerPlantControl() {}
}
