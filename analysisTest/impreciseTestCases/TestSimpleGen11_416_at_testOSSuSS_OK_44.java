public class TestSimpleGen11_416_at_testOSSuSS_OK_44{
  public static void main(String[] args) {
    testOSSuSS_OK_44();
  }
  public static void testOSSuSS_OK_44() {
    int start = 0;
    // start = [0,0]  (checked values 0 0 0)
    for (int i0 = 0; i0 < 8; ++i0) {
      int x1 = i0 | start;
      // x1 = [0,7]  (checked values 0 7 6)
      for (int i2 = 0; i2 <= 6; ++i2) {
        int x3 = x1 << i2;
        // x3 = [0,448]  (checked values 0 448 12)
        for (int i4 = 0; i4 <= 6; ++i4) {
          int x5 = x3 << i4;
          // x5 = [0,28672]  (checked values 0 28672 24)
          for (int i6 = 15; i6 <= 16; ++i6) {
            if (i6 == 16 && x5 <= 28672) continue;
            int x7 = new AircraftControl().readSensor(i6);
            // x7 = [-999,999]  (checked values 999 -457 -999)
            for (int i8 = 0; i8 <= 6; ++i8) {
              int x9 = x7 << i8;
              // x9 = [-63936,63936]  (checked values 999 -29248 -1998)
              for (int i10 = 0; i10 <= 6; ++i10) {
                int x11 = x9 << i10;
                // x11 = [-4091904,4091904]  (checked values 999 -1871872 -3996)
                int index12 = 0;
                if (x11 == 999) { index12 = -999; }
                if (x11 == -1871872) { index12 = 999; }
                if (x11 == -3996) { index12 = 999; }
                if (x11 == -4091905) { index12 = -1000; }
                if (x11 == 4091905) { index12 = 1000; }
                new AircraftControl().adjustValue(11, index12);
              }
            }
          }
        }
      }
    }
  }

  // Test method with AboveEnd array accesses
}