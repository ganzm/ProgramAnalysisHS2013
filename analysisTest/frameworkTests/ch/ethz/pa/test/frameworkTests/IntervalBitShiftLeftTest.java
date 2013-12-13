package ch.ethz.pa.test.frameworkTests;

import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.logging.LoggerUtil;

public class IntervalBitShiftLeftTest {

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	@Test
	public void testShiftLeft() {
		Interval iRes = null;
		String str = null;
		int expected;
		int expected2;
		int input;
		int input2;

		// shift by 0
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(0));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 1
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000010";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(1));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 32 = 0
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(32));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift interval by 1
		str = "00000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000001000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

		// shift interval by 1 or 2
		str = "00000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000010000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1, 2));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

	}

	@Test
	public void testNegativeShiftLeft() {
		Interval iRes = null;
		String str = null;
		int expected;
		int expected2;
		int input;
		int input2;

		// shift by 0
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(0));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 1
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000010";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(1));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 32 = 0
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(32));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift interval by 1
		str = "10000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000001000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

		// shift interval by 1 or 2
		str = "10000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000010000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1, 2));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

	}

	@Test
	public void testSomeFunnyBitShifts() {

		System.out.println(BinaryUtil.toBinString(-1) + "= -1");
		System.out.println(BinaryUtil.toBinString(1 << -1) + "= 1 << -1");
		System.out.println(BinaryUtil.toBinString(1 << -2) + "= 1 << -2");
		System.out.println(BinaryUtil.toBinString(1 << 31) + "= 1 << 31");
		System.out.println(BinaryUtil.toBinString(1 << -31) + "= 1 << -31");
		System.out.println(BinaryUtil.toBinString(1 << 32) + "= 1 << 32");
		System.out.println(BinaryUtil.toBinString(1 << 33) + "= 1 << 33");
		System.out.println(BinaryUtil.toBinString(1 << -32) + "= 1 << -32");
		System.out.println(BinaryUtil.toBinString(1 << 0) + "= 1 << 32");
		System.out.println(BinaryUtil.toBinString(127 << -1) + "= 127 << -1");

		System.out.println(BinaryUtil.toBinString(-1 >>> -1) + "= -1 >>> -1");
		System.out.println(BinaryUtil.toBinString(-1 >>> -2) + "= -1 >>> -2");
		System.out.println(BinaryUtil.toBinString(-1 >>> 31) + "= -1 >>> 31");
		System.out.println(BinaryUtil.toBinString(-1 >>> 32) + "= -1 >>> 32");
		System.out.println(BinaryUtil.toBinString(-1 >>> -32) + "= -1 >>> -32");
		System.out.println(BinaryUtil.toBinString(-1 >>> 0) + "= -1 >>> 32");
	}

	/**
	 * Test some bit-or with random intervals, and print out precision information.
	 */
	@Test
	public void testOrExpensive() {
		for (int i = 0; i < 100; i++) {
			Random rnd = new Random();
			final int i1lo = rnd.nextInt(1000) - 500;
			final int i1hi = i1lo + rnd.nextInt(30);
			final int i2lo = rnd.nextInt(30);
			final int i2hi = i2lo + rnd.nextInt(4);
			assertIntervalShl(i1lo, i1hi, i2lo, i2hi);
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
	public void assertIntervalShl(final int i1lo, final int i1hi, final int i2lo, final int i2hi) {
		Interval interval1 = new Interval(i1lo, i1hi);
		Interval interval2 = new Interval(i2lo, i2hi);
		Interval shlInterval = Interval.shiftLeft(interval1, interval2);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i1 = i1lo; i1 <= i1hi; i1++) {
			for (int i2 = i2lo; i2 <= i2hi; i2++) {
				final int concreteOr = i1 << i2;
				if (!shlInterval.covers(concreteOr)) {
					Assert.fail("failed " + interval1 + " << " + interval2 + " apr " + shlInterval + " exp " + concreteOr);
				}
				min = Math.min(min, concreteOr);
				max = Math.max(max, concreteOr);
			}
		}
		Interval precise = new Interval(min, max);
		System.out.println("testing " + interval1 + " << " + interval2 + " is " + precise + " apr " + shlInterval);
	}

}
