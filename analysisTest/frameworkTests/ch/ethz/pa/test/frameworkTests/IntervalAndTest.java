package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.intervals.Interval;

public class IntervalAndTest {

	@Test
	public void testAnd() {
		for (int a = -16; a < 16; a++) {
			for (int b = 0; b < 32; b++) {
				for (int c = -16; c < 16; c++) {
					for (int d = 0; d < 32; d++) {
						testAndSingleInterval(a, a + b, c, c + d);
					}
				}
			}
		}

	}

	/**
	 * 
	 * @param l1
	 *            lower bound of the first interval
	 * @param u1
	 *            upper bound of the first interval
	 * @param l2
	 *            lower bound of the second interval
	 * @param u2
	 *            upper bound of the second interval
	 */
	private void testAndSingleInterval(int l1, int u1, int l2, int u2) {

		Interval i1 = new Interval(l1, u1);
		Interval i2 = new Interval(l2, u2);

		// perform the AND operation
		Interval res = Interval.and(i1, i2);

		// check every possible value which may occure in these two intervals
		for (int j1 = l1; j1 <= u1; j1++) {
			for (int j2 = l2; j2 <= u2; j2++) {

				int anded = j1 & j2;

				if (!res.covers(anded)) {
					// do it again, so we can debug it
					res = Interval.and(i1, i2);
					String msg = "Test Interval1 " + i1 + " and Interval2 " + i2 + "\nFailes for Values " + j1 + " and " + j2;
					Assert.assertTrue(msg, res.covers(anded));
				}
			}
		}
	}

	@Test
	public void testSpecific1() {
		testSpecific(-16, -15, -16, -15, -15, -15);
	}

	@Test
	public void testSpecific2() {
		testSpecific(-16, -6, -8, 9, -15, -15);
	}

	@Test
	public void testSpecific3() {
		testSpecific(-999, 999, -999, 999, -1024, 1023);
	}

	// Assert.assertTrue(new Interval(-1024, 1023).covers(Interval.and(new Interval(-999, 999), new
	// Interval(-999, 999))));

	private void testSpecific(int i1l, int i1u, int i2l, int i2u, int val1, int val2) {
		Interval i1 = new Interval(i1l, i1u);
		Interval i2 = new Interval(i2l, i2u);
		Interval iRes = Interval.and(i1, i2);

		int toCheck = val1 & val2;

		System.out.println("Interval1 " + i1.toBinString());
		System.out.println("Interval2 " + i2.toBinString());
		System.out.println("Result " + iRes.toBinString());
		System.out.println(BinaryUtil.toBinString(toCheck));

		String msg = "Test Interval1 " + i1 + " and Interval2 " + i2 + "\nFailes";
		Assert.assertTrue(msg, iRes.covers(toCheck));
	}
}
