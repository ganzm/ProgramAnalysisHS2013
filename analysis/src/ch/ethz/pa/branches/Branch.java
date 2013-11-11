package ch.ethz.pa.branches;

import ch.ethz.pa.TriState;

public class Branch {
	
	private final TriState conditionResult;
	
	public Branch(TriState conditionResult) {
		this.conditionResult = conditionResult;
	}

	public TriState getConditionResult() {
		return conditionResult;
	}

}
