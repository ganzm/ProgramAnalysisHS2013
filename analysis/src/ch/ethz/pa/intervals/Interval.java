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

	@Override
	public String toString() {
		if (this == EMPTY_INTERVAL)
			return "[]";
		if (this.upper == this.lower)
			return String.format("[%d]", lower);
		return String.format("[%d,%d]", lower, upper);
	}

	public static Interval plus(Interval i1, Interval i2) {
		if (i1.equals(TOP_INTERVAL) || i2.equals(TOP_INTERVAL))
			return TOP_INTERVAL;
		// TODO: Handle overflow.
		return new Interval(i1.lower + i2.lower, i1.upper + i2.upper);
	}

	public static Interval subtract(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		return new Interval(i1.lower - i2.upper, i1.upper - i2.lower);
	}

	public static Interval multiply(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		int x1 = i1.lower * i2.lower;
		int x2 = i1.lower * i2.upper;
		int x3 = i1.upper * i2.lower;
		int x4 = i1.upper * i2.upper;
		int lower = Math.min(Math.min(x1, x2), Math.min(x3, x4));
		int upper = Math.max(Math.max(x1, x2), Math.max(x3, x4));
		return new Interval(lower, upper);
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
		return new Interval(-upper, -lower);
	}

	public static Interval divide(Interval i1, Interval i2) {
		if (i2.upper < 0) {
			return new Interval(i1.upper / i2.lower, i1.lower / i2.lower);
		}
		if (i2.lower > 0) {
			return new Interval(i1.lower / i2.upper, i1.upper / i2.upper);
		}
		throw new RuntimeException("potential division by zero not supported");
	}

	public static Interval remainder(Interval dividend, Interval divisor) {

		// -------------------------------------------------
		// EXCEPTIONAL CASES WHERE THE INPUT IS UNSOUND

		// if empty intervals appear, the result is empty as well
		if (dividend == EMPTY_INTERVAL || divisor == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;

		// if 0 is a possible divisor, we post a division by zero and return empty
		if (divisor.lower <= 0 && 0 <= divisor.upper) {
			// TODO report division by zero
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
}
