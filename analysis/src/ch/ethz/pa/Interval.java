package ch.ethz.pa;


public class Interval {
	
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
	
	public void copyFrom(Interval other) {
		if (other.lower>other.upper) throw new RuntimeException("interval range corrupt");
		lower = other.lower;
		upper = other.upper;
	}
	
	public static Interval plus(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		return new Interval(i1.lower + i2.lower, i1.upper + i2.upper);
	}

	public static Interval subtract(Interval i1, Interval i2) {
		// TODO Auto-generated method stub
		return new Interval(i1.lower - i2.upper, i1.upper - i2.lower);
	}

	public static Interval multiply(Interval i1, Interval i2) {
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
		Interval i = (Interval)o;
		return lower == i.lower && upper == i.upper;
	}

	// TODO: Do you need to handle infinity or empty interval?
	int lower, upper;

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

	public Interval limitToGreaterEqual(Interval i2) {
		if (lower >= i2.lower) return this;
		if (upper >= i2.lower) return new Interval(i2.lower, upper);
		// TODO Auto-generated method stub
		return null;
	}

	public Interval limitToLower(Interval i2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Interval limitToLowerEqual(Interval i1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Interval limitToGreater(Interval i1) {
		// TODO Auto-generated method stub
		return null;
	}
}
