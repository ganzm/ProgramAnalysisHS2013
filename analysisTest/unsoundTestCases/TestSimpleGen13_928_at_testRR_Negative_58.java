public class TestSimpleGen13_928_at_testRR_Negative_58{
  public static void main(String[] args) {
    testRR_Negative_58();
  }
  public static void testRR_Negative_58() {
    int start = 78;
    // start = [78,78]  (checked values 78 78 78)
    for (int i0 = -3; i0 <= 3; ++i0) {
      int x1 = (start + 2) * (start + 1);
      // x1 = [6320,6320]
      int x2 = (x1 + 2) * (x1 + 1);
      // x2 = [39961362,39961362]
      int x3 = (x2 + 2) * (x2 + 1);
      // x3 = [-1217546628,-1217546628]
      int x4 = (x3 + 2) * (x3 + 1);
      // x4 = [1442164614,1442164614]
      int x5 = x4 << (i0 + 4);
      // x5 = [-1410638068,2104222080]
      int x6 = x5 >> (i0 + 3);
      // x5 = [-1410638068,2104222080]
      // x5 = [-1410638068,2104222080]
      // x5 = [-1410638068,2104222080]
      // x5 = [-1410638068,2104222080]
      int x10 = x5 >> (i0 + 3);
      // x5 = [-1410638068,2104222080]
      // x5 = [-1410638068,2104222080]  (checked values -1410638068 24996833 368422790)
      for (int i11 = -3; i11 <= 3; ++i11) {
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        int x14 = x5 >> (i11 + 3);
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]
        // x5 = [-1410638068,2104222080]  (checked values -1410638068 3124604 184211395)
        int index22 = 0;
        if (x5 == -1410638068) { index22 = -1; }
        if (x5 == 3124604) { index22 = 14; }
        if (x5 == 184211395) { index22 = 14; }
        if (x5 == -1410638069) { index22 = -2; }
        if (x5 == 2104222081) { index22 = 15; }
        new AircraftControl().readSensor(index22);
      }
    }
  }

  // Test method with NegativeAboveEnd array accesses
}