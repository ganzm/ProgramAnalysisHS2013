package ch.ethz.pa.test.frameworkTests;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.intervals.Interval;

public class IntervalTests {

	@Test
	public void testValueInRange() {
		Assert.assertTrue("lower bound should be covered", new Interval(10, 15).covers(10));
		Assert.assertTrue("upper bound should be covered", new Interval(10, 15).covers(15));
	}

	@Test
	public void testValueOutOfRange() {
		Assert.assertFalse("lower bound should be covered", new Interval(10, 15).covers(9));
		Assert.assertFalse("upper bound should be covered", new Interval(10, 15).covers(16));
	}

	@Test
	public void testRangeInRange() {
		Assert.assertTrue("subset should be covered", new Interval(10, 15).covers(new Interval(11, 14)));
		Assert.assertTrue("equal set should be covered", new Interval(10, 15).covers(new Interval(10, 15)));
	}

	@Test
	public void testRangeOutOfRange() {
		Assert.assertFalse("underrun not considered", new Interval(10, 15).covers(new Interval(9, 15)));
		Assert.assertFalse("overrun not considered", new Interval(10, 15).covers(new Interval(10, 16)));
	}

	@Test
	public void testMultiply() {
		Assert.assertEquals(new Interval(-9, 3), Interval.multiply(new Interval(-3, 1), new Interval(-1, 3)));
		Assert.assertEquals(new Interval(-4, 0), Interval.multiply(new Interval(-2, 0), new Interval(0, 2)));
		Assert.assertEquals(new Interval(0, 0), Interval.multiply(new Interval(-5, 5), new Interval(0, 0)));
	}

	@Test
	public void testDivide() {
		boolean[] flag = new boolean[] { false };
		Assert.assertEquals(new Interval(-8, 4), Interval.divide(new Interval(-80, 40), new Interval(2, 10), flag));
		Assert.assertEquals(new Interval(-12, 4), Interval.divide(new Interval(-20, 60), new Interval(-5, -2), flag));
	}

	@Test
	public void testRemainder() {
		boolean[] flag = new boolean[] { false };

		// conformance with java's sign convention implementation
		for (int dividend : new int[] { -27, -7, 7, 27 }) {
			for (int divisor : new int[] { -15, 15 }) {
				Assert.assertEquals(new Interval(dividend % divisor), Interval.remainder(new Interval(dividend), new Interval(divisor), flag));
			}
		}

		// dividend crosses zero
		// twice, once with positive, then with negative dividend
		// (this is critical because of java's sign convention)
		for (Interval divisor : new Interval[] { new Interval(100, 150), new Interval(-150, -100) }) {
			// dividend is within minimum divisor magnitude
			Assert.assertEquals(new Interval(-80, 90), Interval.remainder(new Interval(-80, 90), divisor, flag));
			// dividend within minimum and maximum divisor magnitude
			Assert.assertEquals(new Interval(-110, 120), Interval.remainder(new Interval(-110, 120), divisor, flag));
			// dividend exceeds negative divisor magnitude
			Assert.assertEquals(new Interval(-150, 90), Interval.remainder(new Interval(-170, 90), divisor, flag));
			Assert.assertEquals(new Interval(-150, 120), Interval.remainder(new Interval(-170, 120), divisor, flag));
			// dividend exceeds positive divisor magnitude
			Assert.assertEquals(new Interval(-80, 150), Interval.remainder(new Interval(-80, 170), divisor, flag));
			Assert.assertEquals(new Interval(-110, 150), Interval.remainder(new Interval(-110, 170), divisor, flag));
			// dividend exceed divisor completely
			Assert.assertEquals(new Interval(-150, 150), Interval.remainder(new Interval(-170, 170), divisor, flag));
		}

		// simple cases where divisor is unique (has only one element) and dividend is positive
		Assert.assertEquals(new Interval(3, 79), Interval.remainder(new Interval(3, 79), new Interval(100), flag));
		Assert.assertEquals(new Interval(3, 79), Interval.remainder(new Interval(203, 279), new Interval(100), flag));
		Assert.assertEquals(new Interval(0, 99), Interval.remainder(new Interval(0, 99), new Interval(100), flag));
		Assert.assertEquals(new Interval(0, 99), Interval.remainder(new Interval(200, 299), new Interval(100), flag));

		// simple cases where divisor is unique (has only one element) and dividend is negative
		Assert.assertEquals(new Interval(-79, -3), Interval.remainder(new Interval(-79, -3), new Interval(100), flag));
		Assert.assertEquals(new Interval(-79, -3), Interval.remainder(new Interval(-279, -203), new Interval(100), flag));
		Assert.assertEquals(new Interval(-99, 0), Interval.remainder(new Interval(-99, 0), new Interval(100), flag));
		Assert.assertEquals(new Interval(-99, 0), Interval.remainder(new Interval(-299, -200), new Interval(100), flag));

		// simple cases where dividend is normalized (clearly within truncation range of the
		// divisor)
		Assert.assertEquals(new Interval(3, 79), Interval.remainder(new Interval(3, 79), new Interval(100, 200), flag));
		Assert.assertEquals(new Interval(0, 99), Interval.remainder(new Interval(0, 99), new Interval(100, 200), flag));
		Assert.assertEquals(new Interval(-79, -3), Interval.remainder(new Interval(-79, -3), new Interval(100, 200), flag));
		Assert.assertEquals(new Interval(-99, 0), Interval.remainder(new Interval(-99, 0), new Interval(100, 200), flag));
	}

	@Test
	public void testSubtract() {
		Assert.assertEquals(new Interval(-3, 4), Interval.subtract(new Interval(-1, 5), new Interval(1, 2)));
	}

	@Test
	public void testNegate() {
		Assert.assertEquals(new Interval(-3, 7), new Interval(-7, 3).negate());
	}

	@Test(expected = Exception.class)
	public void testInvalidIntervalThrowsException() {
		new Interval(5, 4);
	}

	@Test
	public void testLimitToGreaterEqual() {
		Assert.assertEquals(new Interval(1), new Interval(1).limitToGreaterEqual(new Interval(1)));
		Assert.assertEquals(new Interval(3, 4), new Interval(3, 4).limitToGreaterEqual(new Interval(1, 2)));
		Assert.assertEquals(new Interval(2, 4), new Interval(2, 4).limitToGreaterEqual(new Interval(1, 3)));
		Assert.assertEquals(new Interval(2, 4), new Interval(1, 4).limitToGreaterEqual(new Interval(2, 3)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1, 2).limitToGreaterEqual(new Interval(3, 4)));
	}

	@Test
	public void testLimitToGreater() {
		Assert.assertEquals(new Interval(3, 4), new Interval(3, 4).limitToGreater(new Interval(0, 1)));
		Assert.assertEquals(new Interval(2, 4), new Interval(2, 4).limitToGreater(new Interval(0, 2)));
		Assert.assertEquals(new Interval(2, 4), new Interval(1, 4).limitToGreater(new Interval(1, 2)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1, 2).limitToGreater(new Interval(2, 3)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1).limitToGreater(new Interval(1)));
	}

	@Test
	public void testLimitToLowerEqual() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(3, 4).limitToLowerEqual(new Interval(1, 2)));
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 4).limitToLowerEqual(new Interval(1, 3)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 4).limitToLowerEqual(new Interval(2, 3)));
		Assert.assertEquals(new Interval(1, 2), new Interval(1, 2).limitToLowerEqual(new Interval(3, 4)));
		Assert.assertEquals(new Interval(1), new Interval(1).limitToLowerEqual(new Interval(1)));
	}

	@Test
	public void testLimitToLower() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1).limitToLower(new Interval(1)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(3, 4).limitToLower(new Interval(2, 3)));
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 4).limitToLower(new Interval(2, 4)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 4).limitToLower(new Interval(3, 4)));
		Assert.assertEquals(new Interval(1, 2), new Interval(1, 2).limitToLower(new Interval(4, 5)));
	}

	@Test
	public void testLimitToEqual() {
		// disjunct sets
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1).limitToEqual(new Interval(2)));

		// enclosing sets (back and forth)
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 3).limitToEqual(new Interval(1, 4)));
		Assert.assertEquals(new Interval(2, 3), new Interval(1, 4).limitToEqual(new Interval(2, 3)));

		// overlapping sets (back and forth)
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 4).limitToEqual(new Interval(1, 3)));
		Assert.assertEquals(new Interval(2, 3), new Interval(1, 3).limitToEqual(new Interval(2, 4)));
	}

	@Test
	public void testLimitToNotEqual() {
		// disjunct sets
		Assert.assertEquals(new Interval(1, 2), new Interval(1, 2).limitToNotEqual(new Interval(3, 4)));
		Assert.assertEquals(new Interval(3, 4), new Interval(3, 4).limitToNotEqual(new Interval(1, 2)));

		// overlapping sets
		Assert.assertEquals(new Interval(1, 4), new Interval(1, 4).limitToNotEqual(new Interval(3, 6)));
		Assert.assertEquals(new Interval(3, 6), new Interval(3, 6).limitToNotEqual(new Interval(1, 4)));

		// equal singleton set
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(3).limitToNotEqual(new Interval(3)));

		// enclosed sets
		Assert.assertEquals(new Interval(2, 4), new Interval(2, 4).limitToNotEqual(new Interval(0, 6)));
		Assert.assertEquals(new Interval(0, 6), new Interval(0, 6).limitToNotEqual(new Interval(2, 4)));
	}

	@Test
	public void testEmptyStaysEmpty() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToLower(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToLower(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToGreater(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToGreater(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToLowerEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToLowerEqual(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToGreaterEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToGreaterEqual(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToEqual(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToNotEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToNotEqual(Interval.EMPTY_INTERVAL));
	}

	@Test
	public void testJoin() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.join(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(new Interval(1, 3), Interval.EMPTY_INTERVAL.join(new Interval(1, 3)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 3).join(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(new Interval(1, 5), new Interval(1, 2).join(new Interval(4, 5)));
	}

	@Test
	public void testGoesLower() {
		// the loop makes sure that the result is independent from the upper bound
		for (int upper : new int[] { 5, 6, 7 }) {
			Assert.assertTrue(new Interval(2, 6).goesLowerThan(new Interval(3, upper)));
			Assert.assertFalse(new Interval(3, 6).goesLowerThan(new Interval(3, upper)));
			Assert.assertFalse(new Interval(4, 6).goesLowerThan(new Interval(3, upper)));
		}
	}

	@Test
	public void testGoesHigher() {
		// the loop makes sure that the result is independent from the lower bound
		for (int lower : new int[] { 5, 6, 7 }) {
			Assert.assertFalse(new Interval(6, 8).goesHigherThan(new Interval(lower, 9)));
			Assert.assertFalse(new Interval(6, 9).goesHigherThan(new Interval(lower, 9)));
			Assert.assertTrue(new Interval(6, 10).goesHigherThan(new Interval(lower, 9)));
		}
	}

	private Interval minus1(final Interval positiveRange) {
		return Interval.plus(positiveRange, new Interval(-1));
	}

	private Interval plus1(final Interval positiveRange) {
		return Interval.plus(positiveRange, new Interval(1));
	}

	@Test
	public void testOverflow() {

		final Interval positiveRange = new Interval(1, Integer.MAX_VALUE);
		final Interval negativeRange = new Interval(Integer.MIN_VALUE, -1);

		// by addition
		Assert.assertEquals(positiveRange, plus1(minus1(positiveRange)));
		Assert.assertEquals(negativeRange, minus1(plus1(negativeRange)));
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.plus(positiveRange, new Interval(1)));
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.plus(negativeRange, new Interval(-1)));
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.plus(Interval.TOP_INTERVAL, new Interval(1)));
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.plus(Interval.TOP_INTERVAL, new Interval(-1)));

		// by negation
		Assert.assertEquals(Integer.MIN_VALUE, -Integer.MIN_VALUE);
		Assert.assertEquals(new Interval(-Integer.MAX_VALUE, -1), positiveRange.negate());
		Assert.assertEquals(Interval.TOP_INTERVAL, negativeRange.negate());
		Assert.assertEquals(new Interval(0, Integer.MAX_VALUE), plus1(negativeRange).negate());
	}

	@Test
	public void testBitRange() {
		Assert.assertEquals(new Interval(0), new Interval(0).bitRange());
		Assert.assertEquals(new Interval(6), new Interval(6).bitRange());
		Assert.assertEquals(new Interval(0, 7), new Interval(0, 7).bitRange());
		Assert.assertEquals(new Interval(0, 7), new Interval(0, 4).bitRange());
		Assert.assertEquals(new Interval(8, 15), new Interval(8, 15).bitRange());
		Assert.assertEquals(new Interval(8, 15), new Interval(9, 14).bitRange());

		Assert.assertEquals(new Interval(-5), new Interval(-5).bitRange());
		Assert.assertEquals(new Interval(-8, -1), new Interval(-8, -1).bitRange());
		Assert.assertEquals(new Interval(-16, -9), new Interval(-16, -9).bitRange());
		Assert.assertEquals(new Interval(-16, -9), new Interval(-15, -10).bitRange());

		Assert.assertEquals(new Interval(Integer.MIN_VALUE, -1), new Interval(Integer.MIN_VALUE, -1).bitRange());
		Assert.assertEquals(new Interval(0, Integer.MAX_VALUE), new Interval(0, Integer.MAX_VALUE).bitRange());

		Assert.assertEquals(Interval.TOP_INTERVAL, new Interval(-1, 1).bitRange());
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.TOP_INTERVAL.bitRange());
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.bitRange());
	}

	/**
	 * Test some bit-and operations in the integer range (Int.MIX...Int.MAX).
	 */
	@Test
	public void testAnd() {
		Assert.assertEquals(new Interval(0), Interval.and(new Interval(0), new Interval(0)));
		Assert.assertEquals(new Interval(0), Interval.and(new Interval(0), new Interval(1)));
		Assert.assertEquals(new Interval(1), Interval.and(new Interval(1), new Interval(1)));
		Assert.assertEquals(new Interval(13 & 26), Interval.and(new Interval(13), new Interval(26)));

		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.and(new Interval(-999, 999), new Interval(-999, 999)));

		Assert.assertEquals(new Interval(2560, 2663), Interval.and(new Interval(2656, 2663), new Interval(2606, 2627)));
		Assert.assertEquals(new Interval(-5120, -4673), Interval.and(new Interval(-4696, -4675), new Interval(-4612, -4598)));
		Assert.assertEquals(new Interval(320, 344), Interval.and(new Interval(328, 357), new Interval(-2728)));
		Assert.assertEquals(new Interval(4608, 4623), Interval.and(new Interval(-1408, -1400), new Interval(4651, 4652)));
	}

	/**
	 * Test some bit-and operations in the instrument value range (-999,999).
	 */
	@Test
	public void testInstrumentAnd() {
		int[][] pairs = new int[][] { new int[] { -999, 999 }, new int[] { -999, -1 }, new int[] { -999, 0 }, new int[] { 0, 999 }, new int[] { 1, 999 },
				new int[] { 0, 511 } };
		for (int idx1 = 0; idx1 < pairs.length; idx1++) {
			int[] pair1 = pairs[idx1];
			for (int idx2 = idx1; idx2 < pairs.length; idx2++) {
				int[] pair2 = pairs[idx2];
				assertIntervalAnd(pair1[0], pair1[1], pair2[0], pair2[1]);
			}
		}
	}

	/**
	 * Test some bit-and with random intervals, and print out precision information.
	 */
	@Test
	public void testAndExpensive() {
		for (int i = 0; i < 100; i++) {
			Random rnd = new Random();
			final int i1lo = rnd.nextInt(10000) - 5000;
			final int i1hi = i1lo + rnd.nextInt(30);
			final int i2lo = rnd.nextInt(10000) - 5000;
			final int i2hi = i2lo + rnd.nextInt(30);
			assertIntervalAnd(i1lo, i1hi, i2lo, i2hi);
		}
	}

	/**
	 * A helper to judge the gap between soundness and precision for bit-and. This actually computes
	 * all "and" combinations (may take a long time depending on the range). It will fail when
	 * unsound. It will also print out the actual "precise" and "approximated" solution, for whoever
	 * cares to read it.
	 * 
	 * @param i1lo
	 * @param i1hi
	 * @param i2lo
	 * @param i2hi
	 */
	public void assertIntervalAnd(final int i1lo, final int i1hi, final int i2lo, final int i2hi) {
		Interval interval1 = new Interval(i1lo, i1hi);
		Interval interval2 = new Interval(i2lo, i2hi);
		Interval andInterval = Interval.and(interval1, interval2);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i1 = i1lo; i1 <= i1hi; i1++) {
			for (int i2 = i2lo; i2 <= i2hi; i2++) {
				final int and = i1 & i2;
				if (!andInterval.covers(and)) {
					Assert.fail("failed " + interval1 + " & " + interval2 + " apr " + andInterval);
				}
				min = Math.min(min, and);
				max = Math.max(max, and);
			}
		}
		Interval precise = new Interval(min, max);
		System.out.println("testing " + interval1 + " & " + interval2 + " is " + precise + " apr " + andInterval);
	}

	/**
	 * Test some bit-xor operations in the integer range (Int.MIX...Int.MAX).
	 */
	@Test
	public void testXor() {
		Assert.assertEquals(new Interval(1 ^ 1), Interval.xor(new Interval(1), new Interval(1)));
		Assert.assertEquals(Interval.TOP_INTERVAL, Interval.xor(Interval.TOP_INTERVAL, new Interval(0)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.xor(Interval.TOP_INTERVAL, Interval.EMPTY_INTERVAL));

		Assert.assertEquals(new Interval(-3904, -3889), Interval.xor(new Interval(-3718, -3717), new Interval(437, 444)));
	}

	/**
	 * Test some bit-and with random intervals, and print out precision information.
	 */
	@Test
	public void testXorExpensive() {
		for (int i = 0; i < 100; i++) {
			Random rnd = new Random();
			final int i1lo = rnd.nextInt(10000) - 5000;
			final int i1hi = i1lo + rnd.nextInt(30);
			final int i2lo = rnd.nextInt(10000) - 5000;
			final int i2hi = i2lo + rnd.nextInt(30);
			assertIntervalXor(i1lo, i1hi, i2lo, i2hi);
		}
	}

	/**
	 * A helper to judge the gap between soundness and precision for bit-and. This actually computes
	 * all "and" combinations (may take a long time depending on the range). It will fail when
	 * unsound. It will also print out the actual "precise" and "approximated" solution, for whoever
	 * cares to read it.
	 * 
	 * @param i1lo
	 * @param i1hi
	 * @param i2lo
	 * @param i2hi
	 */
	public void assertIntervalXor(final int i1lo, final int i1hi, final int i2lo, final int i2hi) {
		Interval interval1 = new Interval(i1lo, i1hi);
		Interval interval2 = new Interval(i2lo, i2hi);
		Interval andInterval = Interval.xor(interval1, interval2);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i1 = i1lo; i1 <= i1hi; i1++) {
			for (int i2 = i2lo; i2 <= i2hi; i2++) {
				final int and = i1 ^ i2;
				if (!andInterval.covers(and)) {
					Assert.fail("failed " + interval1 + " ^ " + interval2 + " apr " + andInterval);
				}
				min = Math.min(min, and);
				max = Math.max(max, and);
			}
		}
		Interval precise = new Interval(min, max);
		System.out.println("testing " + interval1 + " ^ " + interval2 + " is " + precise + " apr " + andInterval);
	}

}
