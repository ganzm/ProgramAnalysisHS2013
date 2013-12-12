/**
 * Just cycle through a loop. Inside, compare with something. This is safe.
 * 
 */
public class GoodLoopTest3 {

	public static void doNothing() {
		int x = 0;
		for (int a = 0; a <= 15; ++a) {
			if (a == 7) {
				x = 1;
			} else {
				x = 2;
			}
		}
	}

}
