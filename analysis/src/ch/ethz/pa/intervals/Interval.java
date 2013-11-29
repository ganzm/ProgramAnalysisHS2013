package ch.ethz.pa.intervals;

/**
 * Interval is a read-only value type.
 */
public class Interval {

	/**
	 * The bottom element of the interval domain.
	 */
	public static final Interval EMPTY_INTERVAL = new Interval();

	/**
	 * The top element of the interval domain.
	 */
	public static final Interval TOP_INTERVAL = new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE);

	/**
	 * This is used to create special intervals, and is not intended for public use.
	 */
	private Interval() {
		this(0);
	};

	public Interval(int start_value) {
		lower = upper = start_value;
	}

	public Interval(int l, int u) {
		if (l > u)
			throw new RuntimeException("interval range corrupt");
		lower = l;
		upper = u;
	}

	/**
	 * returns the number of points included in the interval
	 * 
	 * e.g.
	 * 
	 * [1,2] returns 2
	 * 
	 * [10,10] returns 1
	 * 
	 * [-1, 3] returns 5
	 * 
	 * [-2, -1] returns 2
	 * 
	 * @return
	 */
	public int getNumberOfNumbersInInterval() {
		if (this == EMPTY_INTERVAL || this == TOP_INTERVAL) {
			return Integer.MAX_VALUE;
		}

		return upper - lower + 1;
	}

	@Override
	public String toString() {
		if (this == EMPTY_INTERVAL)
			return "[]";
		if (this.upper == this.lower)
			return String.format("[%d]", lower);
		return String.format("[%d,%d]", lower, upper);
	}

	public static Interval plus(Interval i1, Interval i2) {
		boolean overflow[] = new boolean[] { false };
		int newLower = addCheckOverflow(i1.lower, i2.lower, overflow);
		int newUpper = addCheckOverflow(i1.upper, i2.upper, overflow);
		return overflow[0] ? TOP_INTERVAL : new Interval(newLower, newUpper);
	}

	/**
	 * Perform integer addition and check for overflow.
	 * 
	 * @param a1
	 * @param a2
	 * @param overflow
	 *            set true on overflow, unchanged otherwise
	 * @return
	 */
	private static int addCheckOverflow(int a1, int a2, boolean[] overflow) {
		int result = a1 + a2;
		if ((a2 > 0 && result < a1) || (a2 < 0 && result > a1))
			overflow[0] = true;
		return result;
	}

	public static Interval subtract(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		return new Interval(i1.lower - i2.upper, i1.upper - i2.lower);
	}

	/**
	 * Given four numbers, this returns the smallest interval covering all numbers.
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param x4
	 * @return
	 */
	public static Interval smallestCover(int x1, int x2, int x3, int x4) {
		int lower = Math.min(Math.min(x1, x2), Math.min(x3, x4));
		int upper = Math.max(Math.max(x1, x2), Math.max(x3, x4));
		final Interval interval = new Interval(lower, upper);
		return interval;
	}

	public static Interval multiply(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		int x1 = i1.lower * i2.lower;
		int x2 = i1.lower * i2.upper;
		int x3 = i1.upper * i2.lower;
		int x4 = i1.upper * i2.upper;
		final Interval interval = smallestCover(x1, x2, x3, x4);
		return interval;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Interval))
			return false;
		if (this == o)
			return true;
		Interval i = (Interval) o;
		return lower == i.lower && upper == i.upper;
	}

	/**
	 * Since there are special Intervals (like {@link #EMPTY_INTERVAL}, we make the object immutable
	 * and the fields private.
	 */
	private final int lower;

	/**
	 * Since there are special Intervals (like {@link #EMPTY_INTERVAL}, we make the object immutable
	 * and the fields private.
	 */
	private final int upper;

	public boolean covers(int value) {
		return lower <= value && value <= upper;
	}

	public boolean covers(Interval interval) {
		return lower <= interval.lower && interval.upper <= upper;
	}

	public Interval negate() {
		if (lower == Integer.MIN_VALUE) {
			return TOP_INTERVAL;
		}
		return new Interval(-upper, -lower);
	}

	/**
	 * Determine the interval covering the result of a division. If division by zero is possible,
	 * this returns the {@link #EMPTY_INTERVAL} and sets the flag {@link divisionByZero}.
	 * 
	 * @param i1
	 * @param i2
	 * @param divisionByZero
	 *            set if division by zero is possible, otherwise unchanged
	 * @return
	 */
	public static Interval divide(Interval i1, Interval i2, boolean[] divisionByZero) {
		if (i2.upper < 0) {
			return new Interval(i1.upper / i2.lower, i1.lower / i2.lower);
		}
		if (i2.lower > 0) {
			return new Interval(i1.lower / i2.upper, i1.upper / i2.upper);
		}

		divisionByZero[0] = true;
		return EMPTY_INTERVAL;
	}

	/**
	 * Determine the interval covering the result of a remainder computation. If division by zero is
	 * possible, this returns the {@link #EMPTY_INTERVAL} and sets the flag {@link divisionByZero}.
	 * 
	 * @param dividend
	 * @param divisor
	 * @param divisionByZero
	 * @return
	 */
	public static Interval remainder(Interval dividend, Interval divisor, boolean[] divisionByZero) {

		// -------------------------------------------------
		// EXCEPTIONAL CASES WHERE THE INPUT IS UNSOUND

		// if empty intervals appear, the result is empty as well
		if (dividend == EMPTY_INTERVAL || divisor == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;

		// if 0 is a possible divisor, we post a division by zero and return empty
		if (divisor.lower <= 0 && 0 <= divisor.upper) {
			divisionByZero[0] = true;
			return EMPTY_INTERVAL;
		}

		// from here on, consider only the absolute magnitude of the divisor
		// (the sign of the divisor does not matter)
		final int minDivisor = Math.min(Math.abs(divisor.lower), Math.abs(divisor.upper));
		final int maxDivisor = Math.max(Math.abs(divisor.lower), Math.abs(divisor.upper));

		// -------------------------------------------------
		// SPECIAL CASE: THE DIVIDEND RANGE CROSSES ZERO
		// (here, we have to consider that java preserves the sign of the dividend)

		if (dividend.lower < 0 && 0 < dividend.upper) {

			// dividend potentially exceeds the divisor magnitude on both sides
			if (dividend.lower <= -minDivisor && minDivisor <= dividend.upper)
				return new Interval(Math.max(-maxDivisor, dividend.lower), Math.min(maxDivisor, dividend.upper));

			// dividend potentially exceeds divisor on lower side, but never on upper
			if (dividend.lower <= -minDivisor && dividend.upper < minDivisor)
				return new Interval(Math.max(-maxDivisor, dividend.lower), dividend.upper);

			// dividend potentially exceeds divisor on lower side, but never on upper
			if (-minDivisor < dividend.lower && minDivisor <= dividend.upper)
				return new Interval(dividend.lower, Math.min(maxDivisor, dividend.upper));

			// otherwise, dividend magnitude is always below the divisor magnitude
			return dividend;
		}

		// -------------------------------------------------
		// TRIVIAL CASE: the dividend is normalized negative
		if (-minDivisor < dividend.lower && dividend.lower < 0 && dividend.upper <= 0)
			return dividend;
		// TRIVIAL CASE: the dividend is normalized positive
		if (0 <= dividend.lower && 0 < dividend.upper && dividend.upper < minDivisor)
			return dividend;

		// -------------------------------------------------
		// GENERAL CASE: DIVIDEND RANGE EQUALS OR EXCEEDS THE MINIMUM DIVISOR
		// then anything between 0 and the actual upper divisor is possible
		// but we can exclude sign flipping, which was handled before
		final int dividendRange = dividend.upper - dividend.lower + 1;
		if (dividendRange >= minDivisor)
			return dividend.lower < 0 ? new Interval(1 - maxDivisor, 0) : new Interval(0, maxDivisor - 1);

		// -------------------------------------------------
		// SPECIAL CASE: THE DIVISOR IS UNIQUE
		// (so we can actually compute the remainder for upper and lower bounds)
		// (and we already excluded wrap-around by range)
		if (minDivisor == maxDivisor) {
			final int remainderFromLower = dividend.lower % maxDivisor;
			final int remainderFromUpper = dividend.upper % maxDivisor;
			// only if there was no wrap-around, we gain precision
			// (that is the case when the remainder relation preserves the dividend relation)
			if (remainderFromLower <= remainderFromUpper)
				return new Interval(remainderFromLower, remainderFromUpper);
		}

		// default: return the full range
		return dividend.lower < 0 ? new Interval(1 - maxDivisor, 0) : new Interval(0, maxDivisor - 1);
	}

	public Interval limitToGreaterEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (lower >= other.lower)
			return this;
		if (upper >= other.lower)
			return new Interval(other.lower, upper);
		return EMPTY_INTERVAL;
	}

	public Interval limitToLower(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (upper < other.upper)
			return this;
		if (lower < other.upper)
			return new Interval(lower, other.upper - 1);
		return EMPTY_INTERVAL;
	}

	public Interval limitToLowerEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (upper <= other.upper)
			return this;
		if (lower <= other.upper)
			return new Interval(lower, other.upper);
		return EMPTY_INTERVAL;
	}

	public Interval limitToGreater(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (lower > other.lower)
			return this;
		if (upper > other.lower)
			return new Interval(other.lower + 1, upper);
		return EMPTY_INTERVAL;
	}

	public Interval limitToEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (upper < other.lower || other.upper < lower)
			return EMPTY_INTERVAL;
		return new Interval(Math.max(lower, other.lower), Math.min(upper, other.upper));
	}

	public Interval limitToNotEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;
		if (upper == lower && upper == other.upper && lower == other.lower)
			return EMPTY_INTERVAL;
		return this;
	}

	public Interval join(Interval other) {
		if (this == EMPTY_INTERVAL)
			return other;
		if (other == EMPTY_INTERVAL)
			return this;
		return new Interval(Math.min(lower, other.lower), Math.max(upper, other.upper));
	}

	/**
	 * Returns true if this interval pushes the lower bound compared to the {@link other} interval.
	 * 
	 * @param other
	 * @return
	 */
	public boolean goesLowerThan(Interval other) {
		return lower < other.lower;
	}

	/**
	 * Returns true if this interval pushes the upper bound compared to the {@link other} interval.
	 * 
	 * @param other
	 * @return
	 */
	public boolean goesHigherThan(Interval other) {
		return upper > other.upper;
	}

	/**
	 * Returns the mask of constant bits, i.e., the bits that never change across the range.
	 * 
	 * @return
	 */
	private int maskForConstantBits() {
		int join = lower ^ upper;
		int mask = 0;
		for (int testBit = Integer.MIN_VALUE; testBit != 0 && (join & testBit) == 0; testBit >>>= 1) {
			mask |= testBit;
		}
		return mask;
	}

	/**
	 * First determines the highest common bits of upper and lower. Then returns the interval by
	 * setting and clearing the remaining lower bits.
	 * 
	 * @return
	 */
	public Interval bitRange() {
		return bitRange(maskForConstantBits());
	}

	/**
	 * Returns a new interval, using lower with masked bits cleared and upper with masked bits set.
	 * 
	 * @param mask
	 * @return
	 */
	public Interval bitRange(int mask) {
		if (this.equals(EMPTY_INTERVAL))
			return EMPTY_INTERVAL;
		if (this.equals(TOP_INTERVAL))
			return TOP_INTERVAL;
		if (mask == 0)
			return TOP_INTERVAL;

		int i1 = lower & mask;
		int i2 = (upper & mask) | ~mask;

		return i1 <= i2 ? new Interval(i1, i2) : new Interval(i2, i1);
	}

	/**
	 * XOr for Intervals
	 * 
	 * @param i1
	 * @param i2
	 * @return
	 */
	public static Interval xor(Interval i1, Interval i2) {
		if (i1 == EMPTY_INTERVAL || i2 == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;

		int mask1 = i1.maskForConstantBits();
		int mask2 = i2.maskForConstantBits();
		int commonMask = mask1 & mask2;

		if (commonMask == 0)
			return TOP_INTERVAL;

		Interval br1 = i1.bitRange(commonMask);
		Interval br2 = i2.bitRange(commonMask);
		return smallestCover(br1.lower ^ br2.lower, br1.lower ^ br2.upper, br1.upper ^ br2.lower, br1.upper ^ br2.upper);
	}

	/**
	 * Approximates bit-and for intervals. Here, "approximate" means it is somewhat imprecise, but
	 * still sound.
	 * 
	 * @param i1
	 * @param i2
	 * @return
	 */
	public static Interval and(Interval i1, Interval i2) {
		if (i1 == EMPTY_INTERVAL || i2 == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;

		int mask1 = i1.maskForConstantBits();

		Interval br1 = i1.bitRange();
		Interval br2 = i2.bitRange();
		return smallestCover(br1.lower & br2.lower, br1.lower & br2.upper, br1.upper & br2.lower, br1.upper & br2.upper);
	}
}
