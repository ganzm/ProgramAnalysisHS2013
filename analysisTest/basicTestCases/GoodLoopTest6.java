/**
 * Just cycle through nested loops. This is safe.
 * 
 */
public class GoodLoopTest6 {

	public static void doNothing() {
		int x = 0;
		for (int a = 0; a <= 15; ++a) {
			for (int b = 0; b <= 15; ++b) {
				if (a < 7) {
					x = 1;
				} else {
					x = 2;
				}
			}
		}
	}

}
