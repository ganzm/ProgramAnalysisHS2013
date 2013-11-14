/**
 * Read two values, then compute a rough average. This is SAFE.
 */
public class GoodSimpleWhile1 {

	public static void simpleIfClause() {
		AircraftControl ac = new AircraftControl();
		int v1 = ac.readSensor(13);
		int v2 = ac.readSensor(14);
		while (v1 < v2) {
			v1 = v1 + 1;
			v2 = v2 - 1;
		}
		ac.adjustValue(4, v1);
		ac.adjustValue(5, v2);
	}

}
