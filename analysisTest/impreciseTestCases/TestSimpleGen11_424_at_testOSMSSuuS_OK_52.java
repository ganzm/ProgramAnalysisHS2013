public class TestSimpleGen11_424_at_testOSMSSuuS_OK_52{
  public static void main(String[] args) {
    testOSMSSuuS_OK_52();
  }
  public static void testOSMSSuuS_OK_52() {
    int start = 0;
    // start = [0,0]  (checked values 0 0 0)
    for (int i0 = 0; i0 < 8; ++i0) {
      int x1 = i0 | start;
      // x1 = [0,7]  (checked values 0 7 6)
      for (int i2 = 0; i2 <= 6; ++i2) {
        int x3 = x1 << i2;
        // x3 = [0,448]  (checked values 0 448 12)
        for (int i4 = 99; i4 <= 101; ++i4) {
          int x5 = x3 % i4;
          // x5 = [0,100]  (checked values 0 48 12)
          for (int i6 = 0; i6 <= 6; ++i6) {
            int x7 = x5 << i6;
            // x7 = [0,6400]  (checked values 0 3072 24)
            for (int i8 = 0; i8 <= 6; ++i8) {
              int x9 = x7 << i8;
              // x9 = [0,409600]  (checked values 0 196608 48)
              for (int i10 = 15; i10 <= 16; ++i10) {
                if (i10 == 16 && x9 <= 409600) continue;
                int x11 = new AircraftControl().readSensor(i10);
                // x11 = [-999,999]  (checked values 999 906 -999)
                for (int i12 = 15; i12 <= 16; ++i12) {
                  if (i12 == 16 && x11 <= 999) continue;
                  int x13 = new AircraftControl().readSensor(i12);
                  // x13 = [-999,999]  (checked values 999 57 -999)
                  for (int i14 = 0; i14 <= 6; ++i14) {
                    int x15 = x13 << i14;
                    // x15 = [-63936,63936]  (checked values 999 3648 -1998)
                    int index16 = 0;
                    if (x15 == 999) { index16 = -999; }
                    if (x15 == 3648) { index16 = 999; }
                    if (x15 == -1998) { index16 = 999; }
                    if (x15 == -63937) { index16 = -1000; }
                    if (x15 == 63937) { index16 = 1000; }
                    new AircraftControl().adjustValue(11, index16);
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  // Test method with AboveEnd array accesses
}