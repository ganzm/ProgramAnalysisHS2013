public class GoodAliasedDoubleRead {

	public void doRead() {
		AircraftControl ac1 = new AircraftControl();
		AircraftControl ac2 = ac1;

		ac1.readSensor(0);

		ac2 = new AircraftControl();
		ac2.readSensor(0);
	}
}
