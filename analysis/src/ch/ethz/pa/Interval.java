package ch.ethz.pa;

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

	public static Interval remainder(Interval i1, Interval i2) {
		// if empty intervals appear, the result is empty as well
		if (i1 == EMPTY_INTERVAL || i2 == EMPTY_INTERVAL)
			return EMPTY_INTERVAL;

		// if 0 is a possible divisor, we post a division by zero and return empty
		if (i2.lower <= 0 && 0 <= i2.upper) {
			// TODO report division by zero
			return EMPTY_INTERVAL;
		}

		// from here on, consider only the absolute magnitude of the divisor
		// (the sign of the divisor does not matter)
		final int minDivisor = Math.min(Math.abs(i2.lower), Math.abs(i2.upper));
		final int maxDivisor = Math.max(Math.abs(i2.lower), Math.abs(i2.upper));

		// special case: the dividend crosses zero
		// (here, we have to consider that java preserves the sign of the dividend)
		if (i1.lower < 0 && 0 < i1.upper) {

			// dividend potentially exceeds the divisor magnitude on both sides
			if (i1.lower <= -minDivisor && minDivisor <= i1.upper)
				return new Interval(Math.max(-maxDivisor, i1.lower), Math.min(maxDivisor, i1.upper));

			// dividend potentially exceeds divisor on lower side, but never on upper
			if (i1.lower <= -minDivisor && i1.upper < minDivisor)
				return new Interval(Math.max(-maxDivisor, i1.lower), i1.upper);

			// dividend potentially exceeds divisor on lower side, but never on upper
			if (-minDivisor < i1.lower && minDivisor <= i1.upper)
				return new Interval(i1.lower, Math.min(maxDivisor, i1.upper));

			// otherwise, dividend magnitude is always below the divisor magnitude
			return i1;
		}

		final int dividendRange = i1.upper - i1.lower + 1;

		// special case: the range of the dividend equals or exceeds the the minimum divisor
		// then anything between 0 and the actual upper divisor is possible
		if (dividendRange >= minDivisor)
			return i1.lower < 0 ? new Interval(1 - maxDivisor, 0) : new Interval(0, maxDivisor - 1);

		// special case: the divisor is unique
		// (and as found out previously, the dividend range is below the divisor range)
		if (minDivisor == maxDivisor) {
			final int remainderFromLower = i1.lower % maxDivisor;
			final int remainderFromUpper = i1.upper % maxDivisor;
			if (remainderFromLower <= remainderFromUpper)
				return new Interval(remainderFromLower, remainderFromUpper);
			return new Interval(0, maxDivisor - 1);
		}

		// special case: the dividend is normalized
		if (i1.lower >= 0 && i1.upper < minDivisor)
			return i1;

		// default: return the full range
		return new Interval(0, maxDivisor - 1);
	}

	public static TriState greaterEqual(Interval i1, Interval i2) {
		if (i1.lower >= i2.upper)
			return TriState.True;
		if (i1.upper < i2.lower)
			return TriState.False;
		return TriState.Unknown;
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
