public class TestSimpleGen11_384_at_testOMuM_OK_12{
  public static void main(String[] args) {
    testOMuM_OK_12();
  }
  public static void testOMuM_OK_12() {
    int start = 0;
    // start = [0,0]  (checked values 0 0 0)
    for (int i0 = 0; i0 < 8; ++i0) {
      int x1 = i0 | start;
      // x1 = [0,7]  (checked values 0 7 6)
      for (int i2 = 99; i2 <= 101; ++i2) {
        int x3 = x1 % i2;
        // x3 = [0,7]  (checked values 0 7 6)
        for (int i4 = 15; i4 <= 16; ++i4) {
          if (i4 == 16 && x3 <= 7) continue;
          int x5 = new AircraftControl().readSensor(i4);
          // x5 = [-999,999]  (checked values 999 -10 -999)
          for (int i6 = 99; i6 <= 101; ++i6) {
            int x7 = x5 % i6;
            // x7 = [-100,100]  (checked values 9 -10 -90)
            int index8 = 0;
            if (x7 == 9) { index8 = -999; }
            if (x7 == -10) { index8 = 999; }
            if (x7 == -90) { index8 = 999; }
            if (x7 == -101) { index8 = -1000; }
            if (x7 == 101) { index8 = 1000; }
            new AircraftControl().adjustValue(11, index8);
          }
        }
      }
    }
  }

  // Test method with AboveEnd array accesses
}