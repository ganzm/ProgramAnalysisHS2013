/**
 * Read a value, decrement while above -999, then adjust. This is SAFE.
 */
public class GoodSimpleWhile5 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(13);
		while (v1 > -999) {
			v1 = v1 - 1;
		}
		ac.adjustValue(4, v1);
	}

}
