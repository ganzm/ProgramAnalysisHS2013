public class TestSimpleGen13_891_at_testDMD_AboveEnd_21{
  public static void main(String[] args) {
    testDMD_AboveEnd_21();
  }
  public static void testDMD_AboveEnd_21() {
    int start = 78;
    // start = [78,78]  (checked values 78 78 78)
    for (int i0 = 2; i0 <= 5; ++i0) {
      int x1 = start / i0;
      // x1 = [15,39]  (checked values 39 26 15)
      for (int i2 = 99; i2 <= 101; ++i2) {
        int x3 = x1 % i2;
        // x3 = [15,39]  (checked values 39 26 15)
        for (int i4 = 2; i4 <= 5; ++i4) {
          int x5 = x3 / i4;
          // x5 = [3,19]  (checked values 19 8 3)
          int index6 = 0;
          if (x5 == 19) { index6 = 1; }
          if (x5 == 8) { index6 = 16; }
          if (x5 == 3) { index6 = 16; }
          if (x5 == 2) { index6 = 0; }
          if (x5 == 20) { index6 = 17; }
          new AircraftControl().readSensor(index6);
        }
      }
    }
  }

  // Test method with Negative array accesses
}