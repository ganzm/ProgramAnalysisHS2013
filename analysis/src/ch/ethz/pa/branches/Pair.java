package ch.ethz.pa.branches;

import ch.ethz.pa.Interval;
import ch.ethz.pa.IntervalPerVar;

/**
 * A {@link Pair} of restrictions applying to the left and right side of a
 * branch condition. In contrast to the pair presented in the lecture, this
 * class represents actually two pairs: the named one, and its counterpart (so
 * it contains four intervals, not just two).
 * <p/>
 * 
 * Consider a branch condition <i>a1 &lt;op&gt; a2</i>. The values of <i>a1</i>
 * and <i>a2</i> are given as intervals (or not known at all). Thus, depending
 * on whether the branch conditions holds, the ranges can be restricted.
 * <p/>
 * 
 * There are <b>four</b> {@link Restriction}s:
 * 
 * <li>for <i>a1</i> if the condition holds: {@link #leftArgumentBranchOut}</li>
 * <li>for <i>a2</i> if the condition holds: {@link #rightArgumentBranchOut}</li>
 * <li>for <i>a1</i> if the condition does not hold:
 * {@link #leftArgumentFallOut}</li>
 * <li>for <i>a2</i> if the condition does not hold:
 * {@link #rightArgumentFallOut}</li>
 */
public class Pair {

	/**
	 * A {@link Restriction} hold the restricted {@link range} for variable
	 * identified by {@link name}
	 */
	public static class Restriction {

		public final String name;
		public final Interval interval;

		/**
		 * A {@link Restriction} hold the restricted {@link range} for variable
		 * identified by {@link name}
		 */
		public Restriction(String name, Interval interval) {
			super();
			this.name = name;
			this.interval = interval;
		}
	}

	protected Restriction leftArgumentBranchOut;
	protected Restriction rightArgumentBranchOut;
	protected Restriction leftArgumentFallOut;
	protected Restriction rightArgumentFallOut;

	private void apply(Restriction restriction, IntervalPerVar store) {
		if (restriction != null) {
			store.putIntervalForVar(restriction.name, restriction.interval);
		}
	}

	/**
	 * When branching, i.e., when the condition does hold, apply the
	 * restrictions for affected intervals.
	 */
	public void restrictBranchState(IntervalPerVar branchState) {
		apply(leftArgumentBranchOut, branchState);
		apply(rightArgumentBranchOut, branchState);
	}

	/**
	 * When falling through, i.e., when the condition does not hold, apply the
	 * restrictions for affected intervals.
	 */
	public void restrictFallstate(IntervalPerVar fallState) {
		apply(leftArgumentFallOut, fallState);
		apply(rightArgumentFallOut, fallState);
	}

}
