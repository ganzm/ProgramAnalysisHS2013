package ch.ethz.pa.branches;

import ch.ethz.pa.IntervalPerVar;
import ch.ethz.pa.TriState;

public abstract class Branch {
	
	private final TriState conditionResult;
	
	public Branch(TriState conditionResult) {
		this.conditionResult = conditionResult;
	}

	// TODO is this obsolete?
	public TriState getConditionResult() {
		return conditionResult;
	}

	/**
	 * When branching, i.e., when the condition does hold,
	 * apply the restrictions for affected intervals.
	 */
	public abstract void restrictBranchState(IntervalPerVar branchState);

	/**
	 * When falling through, i.e., when the condition does not hold,
	 * apply the restrictions for affected intervals.
	 */
	public abstract void restrictFallstate(IntervalPerVar fallState);


}
