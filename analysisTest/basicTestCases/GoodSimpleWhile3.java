/**
 * Read a value, increment while below 999, then adjust. This is SAFE.
 */
public class GoodSimpleWhile3 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(13);
		while (v1 < 999) {
			v1 = v1 + 1;
		}
		ac.adjustValue(4, v1);
	}

}
