public class GoodReadTwice {

	public void doRead() {
		AircraftControl ac1 = new AircraftControl();
		AircraftControl ac2 = new AircraftControl();

		ac1.readSensor(0);
		ac2.readSensor(0);

		ac1.readSensor(1);
		ac2.readSensor(1);

		// Problem here
		ac1.readSensor(2);
		ac2.readSensor(2);

		ac1.readSensor(4);
		ac2.readSensor(4);

		ac1.readSensor(5);
		ac2.readSensor(5);

		ac1.readSensor(6);
		ac2.readSensor(6);

		ac1.readSensor(7);
		ac2.readSensor(7);

		ac1.readSensor(8);
		ac2.readSensor(8);

		ac1.readSensor(9);
		ac2.readSensor(9);

		ac1.readSensor(10);
		ac2.readSensor(10);

		ac1.readSensor(11);
		ac2.readSensor(11);

		ac1.readSensor(12);
		ac2.readSensor(12);

		ac1.readSensor(13);
		ac2.readSensor(13);

		ac1.readSensor(14);
		ac2.readSensor(14);

		ac1.readSensor(15);
		ac2.readSensor(15);
	}

}
