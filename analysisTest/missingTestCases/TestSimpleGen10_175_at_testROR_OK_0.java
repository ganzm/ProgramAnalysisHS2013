public class TestSimpleGen10_175_at_testROR_OK_0{
  public static void main(String[] args) {
    testROR_OK_0();
  }
  public static void testROR_OK_0() {
    int start = 45;
    // start = [45,45]  (checked values 45 45 45)
    for (int i0 = -3; i0 <= 3; ++i0) {
      int x1 = (start + 2) * (start + 1);
      // x1 = [2162,2162]
      int x2 = x1 << (i0 + 4);
      // x2 = [4324,276736]
      int x3 = x2 << (i0 + 4);
      // x3 = [8648,35422208]
      // x3 = [8648,35422208]
      // x3 = [8648,35422208]
      // x3 = [8648,35422208]
      int x7 = x3 >> (i0 + 3);
      // x7 = [135,35422208]
      int x8 = x7 >> (i0 + 3);
      // x8 = [2,35422208]
      // x8 = [2,35422208]
      // x8 = [2,35422208]
      // x8 = [2,35422208]  (checked values 8648 8648 8648)
      for (int i11 = 0; i11 < 8; ++i11) {
        int x12 = i11 | x8;
        // x12 = [-2147483648,2147483647]  (checked values 8648 8655 8654)
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
          // x12 = [-2147483648,2147483647]  (checked values 8648 8655 8654)
          int index24 = 0;
          if (x12 == 8648) { index24 = -999; }
          if (x12 == 8655) { index24 = 999; }
          if (x12 == 8654) { index24 = 999; }
          new AircraftControl().adjustValue(11, index24);
        }
      }
    }
  }

  // Test method with AboveEnd array accesses
}