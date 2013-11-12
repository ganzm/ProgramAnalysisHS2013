public class WrongWriteTwice {

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
		ac.adjustValue(7, 0);
		ac.adjustValue(9, 0);
		ac.adjustValue(10, 0);
		ac.adjustValue(11, 0);
		ac.adjustValue(12, 0);
		ac.adjustValue(13, 0);
		ac.adjustValue(14, 0);
		ac.adjustValue(15, 0);
	}
}
