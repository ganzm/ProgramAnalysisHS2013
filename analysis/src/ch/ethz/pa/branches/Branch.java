package ch.ethz.pa.branches;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import ch.ethz.pa.Interval;
import ch.ethz.pa.IntervalPerVar;

public class Branch {
	
	/**
	 * Intervals restricted when the branch condition holds.
	 */
	private Map<String, Interval> branchOutRestrictions;
	
	/**
	 * Intervals restricted when the branch condition does not hold.
	 */
	private Map<String, Interval> fallOutRestrictions;
	
	public Branch() {
		branchOutRestrictions = new TreeMap<String, Interval>();
		fallOutRestrictions = new TreeMap<String, Interval>();
	}
	
	protected void restrictBranchOut(String name, Interval restrictedInterval) {
		branchOutRestrictions.put(name, restrictedInterval);
	}
	
	protected void restrictFallout(String name, Interval restrictedInterval) {
		fallOutRestrictions.put(name, restrictedInterval);
	}

	/**
	 * When branching, i.e., when the condition does hold,
	 * apply the restrictions for affected intervals.
	 */
	public void restrictBranchState(IntervalPerVar branchState) {
		for(Entry<String, Interval> restriction : branchOutRestrictions.entrySet()) {
			branchState.putIntervalForVar(restriction.getKey(), restriction.getValue());
		}
	}

	/**
	 * When falling through, i.e., when the condition does not hold,
	 * apply the restrictions for affected intervals.
	 */
	public void restrictFallstate(IntervalPerVar fallState) {
		for(Entry<String, Interval> restriction : fallOutRestrictions.entrySet()) {
			fallState.putIntervalForVar(restriction.getKey(), restriction.getValue());
		}
	}


}
