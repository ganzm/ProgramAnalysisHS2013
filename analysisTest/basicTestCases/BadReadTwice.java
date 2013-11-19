public class BadReadTwice {

	public void doRead() {
		AircraftControl ac = new AircraftControl();
		ac.readSensor(0);
		ac.readSensor(1);

		// Problem here
		ac.readSensor(2);
		ac.readSensor(2);

		ac.readSensor(4);
		ac.readSensor(5);
		ac.readSensor(6);
		ac.readSensor(7);
		ac.readSensor(8);
		ac.readSensor(9);
		ac.readSensor(10);
		ac.readSensor(11);
		ac.readSensor(12);
		ac.readSensor(13);
		ac.readSensor(14);
		ac.readSensor(15);
	}

}
