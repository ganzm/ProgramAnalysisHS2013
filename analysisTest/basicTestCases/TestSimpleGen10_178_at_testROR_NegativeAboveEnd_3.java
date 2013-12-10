public class TestSimpleGen10_178_at_testROR_NegativeAboveEnd_3 {
	public static void main(String[] args) {
		testROR_NegativeAboveEnd_3();
	}

	public static void testROR_NegativeAboveEnd_3() {
		int start = 45;
		// start = [45,45] (checked values 45 45 45)
		for (int i0 = -3; i0 <= 3; ++i0) {
			int x1 = start * i0;
			// x1 = [-135,135]
			int x2 = x1 * i0;
			// x2 = [-405,405]
			int x3 = x2 << (i0 + 3);
			// x3 = [-25920,25920]
			int x4 = x3 << (i0 + 3);
			// x4 = [-1658880,1658880]
			int x5 = x4 * i0;
			// x5 = [-4976640,4976640]
			int x6 = x5 * i0;
			// x6 = [-14929920,14929920]
			int x7 = x6 << (i0 + 3);
			// x7 = [-955514880,955514880]
			// x7 = [-955514880,955514880]
			// x7 = [-955514880,955514880]
			// x7 = [-955514880,955514880]
			// x7 = [-955514880,955514880] (checked values 3645 1866240 5760)
			for (int i11 = 0; i11 < 8; ++i11) {
				int x12 = i11 | x7;
				// x12 = [-2147483648,2147483647] (checked values 3645 1866247 5766)
				for (int i13 = -3; i13 <= 3; ++i13) {
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647]
					// x12 = [-2147483648,2147483647] (checked values 3645 1866247 5766)
					int index24 = 0;
					if (x12 == 3645) {
						index24 = -1000;
					}
					if (x12 == 1866247) {
						index24 = 1000;
					}
					if (x12 == 5766) {
						index24 = 1000;
					}
					new AircraftControl().adjustValue(11, index24);
				}
			}
		}
	}

	// Test method with OK array accesses
}