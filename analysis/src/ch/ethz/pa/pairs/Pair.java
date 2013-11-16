package ch.ethz.pa.pairs;

import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

/**
 * A {@link Pair} of restrictions applying to the left and right side of a <i>satisfied</i>
 * condition that leads into a branch. I.e., the restrictions are valid <i>inside</i> the branch
 * block.
 * <p/>
 * 
 * Consider a branch condition <i>a1 &lt;op&gt; a2</i>. The values of <i>a1</i> and <i>a2</i> are
 * given as intervals (or not known at all). Thus, when the branch conditions holds, the ranges can
 * be restricted.
 */
public class Pair {

	/**
	 * A {@link Restriction} hold the restricted {@link range} for variable identified by
	 * {@link name}
	 */
	public static class Restriction {

		public final String name;
		public final Interval interval;

		/**
		 * A {@link Restriction} hold the restricted {@link range} for variable identified by
		 * {@link name}
		 */
		public Restriction(String name, Interval interval) {
			super();
			this.name = name;
			this.interval = interval;
		}
	}

	protected Restriction leftArgumentRestriction;
	protected Restriction rightArgumentRestriction;

	private void apply(Restriction restriction, IntervalPerVar store) {
		if (restriction != null) {
			store.putIntervalForVar(restriction.name, restriction.interval);
		}
	}

	/**
	 * When branching, i.e., when the condition does hold, apply the restrictions for affected
	 * intervals.
	 */
	public void restrict(IntervalPerVar branchState) {
		apply(leftArgumentRestriction, branchState);
		apply(rightArgumentRestriction, branchState);
	}
}
