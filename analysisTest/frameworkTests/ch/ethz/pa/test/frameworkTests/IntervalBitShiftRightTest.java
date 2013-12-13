package ch.ethz.pa.test.frameworkTests;

import java.util.Random;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.logging.LoggerUtil;

public class IntervalBitShiftRightTest {

	private final Logger logger = Logger.getLogger(IntervalBitShiftRightTest.class.getSimpleName());

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	@Test
	public void testUnsignedShiftRight() {
		Interval iRes = null;

		Interval i1024 = new Interval(1024);
		Interval i512_1024 = new Interval(512, 1024);
		Interval i1 = new Interval(1);
		Interval i2 = new Interval(2);
		Interval i1_2 = new Interval(1, 2);

		// Default Case
		iRes = Interval.shiftRight(i1024, i1);
		Assert.assertEquals(new Interval(512), iRes);

		// Easy Case
		iRes = Interval.shiftRight(i1024, i2);
		Assert.assertEquals(new Interval(256), iRes);

		// Other Case
		iRes = Interval.shiftRight(i1024, i1_2);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftRight(i512_1024, i1);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftRight(i512_1024, i1_2);
		Assert.assertEquals(new Interval(128, 512), iRes);
	}

	@Test
	public void testNegativeUnsignedShiftRight() {
		Interval iRes = null;
		String expectedString = null;
		int expected1;
		int expected2;

		// no shift by 0
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "10000000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(0));
		Assert.assertEquals(new Interval(expected2), iRes);

		// simple shift by 1
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "11000000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(1));
		Assert.assertEquals(new Interval(expected2), iRes);

		// simple shift by 2
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "11100000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(2));
		Assert.assertEquals(new Interval(expected2), iRes);

	}

	/**
	 * Test some bit-shl with random intervals, and print out precision information.
	 */
	@Test
	public void testShrExpensive() {
		for (int i = 0; i < 100; i++) {
			Random rnd = new Random();
			final int i1base = rnd.nextInt();
			int i1lo, i1hi;
			if (i1base > 0) {
				i1hi = i1base;
				i1lo = i1hi - rnd.nextInt(30);
			} else {
				i1lo = i1base;
				i1hi = i1lo + rnd.nextInt(30);
			}
			final int i2lo = rnd.nextInt(30);
			final int i2hi = i2lo + rnd.nextInt(4);
			assertIntervalShr(i1lo, i1hi, i2lo, i2hi);
		}
	}

	/**
	 * A helper to judge the gap between soundness and precision for bit-or. This actually computes
	 * all "or" combinations (may take a long time depending on the range). It will fail when
	 * unsound. It will also print out the actual "precise" and "approximated" solution, for whoever
	 * cares to read it.
	 * 
	 * @param i1lo
	 * @param i1hi
	 * @param i2lo
	 * @param i2hi
	 */
	public void assertIntervalShr(final int i1lo, final int i1hi, final int i2lo, final int i2hi) {
		Interval interval1 = new Interval(i1lo, i1hi);
		Interval interval2 = new Interval(i2lo, i2hi);
		Interval shlInterval = Interval.shiftRight(interval1, interval2);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i1 = i1lo; i1 <= i1hi; i1++) {
			for (int i2 = i2lo; i2 <= i2hi; i2++) {
				final int concreteShl = i1 >> i2;
				if (!shlInterval.covers(concreteShl)) {
					Assert.fail("failed " + interval1 + " >> " + interval2 + " apr " + shlInterval + " exp " + concreteShl + " (when shifting " + i1 + " by "
							+ i2 + ")");
				}
				min = Math.min(min, concreteShl);
				max = Math.max(max, concreteShl);
			}
		}
		Interval precise = new Interval(min, max);
		System.out.print(precise.covers(shlInterval) ? "precise " : "approx  ");
		System.out.println("testing " + interval1 + " >> " + interval2 + " is " + precise + " apr " + shlInterval);
	}

}
