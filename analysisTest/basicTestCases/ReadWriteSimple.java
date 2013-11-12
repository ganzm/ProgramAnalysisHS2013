public class ReadWriteSimple {

	public void doRead() {
		AircraftControl ac = new AircraftControl();
		ac.readSensor(0);
		ac.readSensor(1);
		ac.readSensor(2);
		ac.readSensor(3);
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

	public void doWrite() {
		AircraftControl ac = new AircraftControl();
		ac.adjustValue(0, 0);
		ac.adjustValue(1, 0);
		ac.adjustValue(2, 0);
		ac.adjustValue(3, 0);
		ac.adjustValue(4, 0);
		ac.adjustValue(5, 0);
		ac.adjustValue(6, 0);
		ac.adjustValue(7, 0);
		ac.adjustValue(8, 0);
		ac.adjustValue(9, 0);
		ac.adjustValue(10, 0);
		ac.adjustValue(11, 0);
		ac.adjustValue(12, 0);
		ac.adjustValue(13, 0);
		ac.adjustValue(14, 0);
		ac.adjustValue(15, 0);
	}
}
