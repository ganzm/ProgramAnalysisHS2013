public class TestSimpleGen10_199_at_testRRR_OK_24_Simplified2 {
	public static void main(String[] args) {
		testRRR_OK_24();
	}

	public static void testRRR_OK_24() {
		int start = 45;
		// start = [45,45] (checked values 45 45 45)
		for (int i0 = -3; i0 <= 1; ++i0) {
			int x1 = start * i0;
			// x1 = [-135,135]
			// x1 = [-135,135]
			int x3 = x1 * i0;
			// x3 = [-405,405]
			// x3 = [-405,405]
			int x5 = x3 << (i0 + 3);
			// x5 = [-25920,25920]
			int x6 = x5 * i0;
			// x6 = [-77760,77760]
			int x7 = x6 << (i0 + 3);
			// x7 = [-4976640,4976640]
			// x7 = [-4976640,4976640]
			int x9 = x7 << (i0 + 3);
			// x9 = [-318504960,318504960]
			// x9 = [-318504960,318504960]
			// x9 = [-318504960,318504960] (checked values -1215 9216 -720)
			for (int i11 = -3; i11 <= 1; ++i11) {
				// x9 = [-318504960,318504960]
				// x9 = [-318504960,318504960]
				// x9 = [-318504960,318504960]
				int x15 = x9 * i11;
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880]
				// x15 = [-955514880,955514880] (checked values 3645 27648 1440)

				int index33 = 0;

				if (x15 == 3645) {
					index33 = -999;
				}
				if (x15 == 432) {
					index33 = 999;
				}
				if (x15 == 360) {
					index33 = 999;
				}
				if (x15 == -955514881) {
					index33 = -1000;
				}
				if (x15 == 955514881) {
					index33 = 1000;
				}

				new AircraftControl().adjustValue(11, index33);
			}
		}
	}

	// Test method with AboveEnd array accesses
}