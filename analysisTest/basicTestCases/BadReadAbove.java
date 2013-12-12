public class BadReadAbove {

	public void doRead() {
		AircraftControl ac = new AircraftControl();
		ac.readSensor(0);
		ac.readSensor(16);
	}

}
