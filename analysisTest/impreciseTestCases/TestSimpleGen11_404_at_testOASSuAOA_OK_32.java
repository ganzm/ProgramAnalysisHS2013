public class TestSimpleGen11_404_at_testOASSuAOA_OK_32{
  public static void main(String[] args) {
    testOASSuAOA_OK_32();
  }
  public static void testOASSuAOA_OK_32() {
    int start = 0;
    // start = [0,0]  (checked values 0 0 0)
    for (int i0 = 0; i0 < 8; ++i0) {
      int x1 = i0 | start;
      // x1 = [0,7]  (checked values 0 7 6)
      for (int i2 = 0; i2 < 8; ++i2) {
        int x3 = i2 & x1;
        // x3 = [0,7]  (checked values 0 7 4)
        for (int i4 = 0; i4 <= 6; ++i4) {
          int x5 = x3 << i4;
          // x5 = [0,448]  (checked values 0 448 8)
          for (int i6 = 0; i6 <= 6; ++i6) {
            int x7 = x5 << i6;
            // x7 = [0,28672]  (checked values 0 28672 16)
            for (int i8 = 15; i8 <= 16; ++i8) {
              if (i8 == 16 && x7 <= 28672) continue;
              int x9 = new AircraftControl().readSensor(i8);
              // x9 = [-999,999]  (checked values 999 -19 -999)
              for (int i10 = 0; i10 < 8; ++i10) {
                int x11 = i10 & x9;
                // x11 = [-2147483648,2147483647]  (checked values 0 5 1)
                for (int i12 = 0; i12 < 8; ++i12) {
                  int x13 = i12 | x11;
                  // x13 = [-2147483648,2147483647]  (checked values 0 7 7)
                  for (int i14 = 0; i14 < 8; ++i14) {
                    int x15 = i14 & x13;
                    // x15 = [-2147483648,2147483647]  (checked values 0 7 5)
                    int index16 = 0;
                    if (x15 == 0) { index16 = -999; }
                    if (x15 == 7) { index16 = 999; }
                    if (x15 == 5) { index16 = 999; }
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