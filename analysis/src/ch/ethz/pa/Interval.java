package ch.ethz.pa;

/**
 * Interval is a read-only value type.
 */
public class Interval {
	
	public static final Interval EMPTY_INTERVAL = new Interval();
	
	/**
	 * This is used to create special intervals, and 
	 * is not intended for public use.
	 */
	private Interval() {
		this(0);
	};

	public Interval(int start_value) {
		lower = upper = start_value;
	}
	
	public Interval(int l, int u) {
		if (l>u) throw new RuntimeException("interval range corrupt");
		lower = l;
		upper = u;
	}
	
	@Override
	public String toString() {
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
		int lower = Math.min(Math.min(x1,x2), Math.min(x3,x4));
		int upper = Math.max(Math.max(x1,x2), Math.max(x3,x4));
		return new Interval(lower, upper);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Interval)) return false;
		if (this == o) return true;
		Interval i = (Interval)o;
		return lower == i.lower && upper == i.upper;
	}

	final int lower;
	final int upper;

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
		if (i2.upper <0) {
			return new Interval(i1.upper / i2.lower, i1.lower/i2.lower);
		}
		if (i2.lower >0) {
			return new Interval(i1.lower / i2.upper, i1.upper / i2.upper);
		}
		throw new RuntimeException("potential division by zero not supported");
	}
	
	public static TriState greaterEqual(Interval i1, Interval i2) {
		if (i1.lower >= i2.upper) return TriState.True;
		if (i1.upper < i2.lower) return TriState.False;
		return TriState.Unknown;
	}

	public Interval limitToGreaterEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL) return EMPTY_INTERVAL;
		if (lower >= other.lower) return this;
		if (upper >= other.lower) return new Interval(other.lower, upper);
		return EMPTY_INTERVAL;
	}

	public Interval limitToLower(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL) return EMPTY_INTERVAL;
		if (upper < other.upper) return this;
		if (lower < other.upper) return new Interval(lower, other.upper-1);  
		return EMPTY_INTERVAL;
	}

	public Interval limitToLowerEqual(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL) return EMPTY_INTERVAL;
		if (upper <= other.upper) return this;
		if (lower <= other.upper) return new Interval(lower, other.upper);  
		return EMPTY_INTERVAL;
	}

	public Interval limitToGreater(Interval other) {
		if (this == EMPTY_INTERVAL || other == EMPTY_INTERVAL) return EMPTY_INTERVAL;
		if (lower > other.lower) return this;
		if (upper > other.lower) return new Interval(other.lower+1, upper);
		return EMPTY_INTERVAL;
	}
}
