package ch.ethz.pa;


public class Interval {
	public Interval(int start_value) {
		lower = upper = start_value;
	}
	
	public Interval(int l, int u) {
		lower = l;
		upper = u;
	}
	
	@Override
	public String toString() {
		return String.format("[%d,%d]", lower, upper);
	}
	
	public void copyFrom(Interval other) {
		lower = other.lower;
		upper = other.upper;
	}
	
	public static Interval plus(Interval i1, Interval i2) {
		// TODO: Handle overflow.
		return new Interval(i1.lower + i2.lower, i1.upper + i2.upper);
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
}
