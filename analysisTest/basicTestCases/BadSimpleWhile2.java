/**
 * Read a value, increment while below 1000, then adjust. This is UNSAFE.
 */
public class BadSimpleWhile2 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(13);
		while (v1 < 1000) {
			v1 = v1 + 1;
		}
		ac.adjustValue(4, v1);
	}

}
