public class TestSimpleGen11_373_at_testu_AboveEnd_1{
  public static void main(String[] args) {
    testu_AboveEnd_1();
  }
  public static void testu_AboveEnd_1() {
    int start = 0;
    // start = [0,0]  (checked values 0 0 0)
    for (int i0 = 15; i0 <= 16; ++i0) {
      if (i0 == 16 && start <= 0) continue;
      int x1 = new AircraftControl().readSensor(i0);
      // x1 = [-999,999]  (checked values 999 542 -999)
      int index2 = x1 + 1;
      new AircraftControl().adjustValue(11, index2);
    }
  }

  // Test method with Negative array accesses
}