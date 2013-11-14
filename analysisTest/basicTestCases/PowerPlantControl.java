/**
 * This class has the same methods as the {@link AircraftControl}, but is not related to it.
 * Therefore, the specific checks of {@link AircraftControl} do not apply to it.
 * <p>
 * 
 * This is <b>NOT A TEST CASE</b>, but servers as a type in a test case.
 */
public class PowerPlantControl {
	public native int readSensor(int sensorId);

	public native void adjustValue(int sensorId, int newValue);

	public PowerPlantControl() {
	}
}
