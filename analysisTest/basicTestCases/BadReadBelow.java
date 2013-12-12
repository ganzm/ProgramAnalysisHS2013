public class BadReadBelow {

	public void doRead() {
		AircraftControl ac = new AircraftControl();
		ac.readSensor(0);
		ac.readSensor(-1);
	}

}
