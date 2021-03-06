package ch.ethz.pa;

import ch.ethz.pa.intervals.IntervalPerVar;

/**
 * Holds the abstract state per code line
 * 
 */
public class StateContainer {

	private IntervalPerVar intervalPerVar;

	private final AirCraftRefPerVar refPerVar;

	public StateContainer() {
		this.intervalPerVar = new IntervalPerVar();
		this.refPerVar = new AirCraftRefPerVar();
	}

	public IntervalPerVar getIntervalPerVar() {
		return intervalPerVar;
	}

	public void copyFrom(StateContainer src1) {
		intervalPerVar.copyFrom(src1.getIntervalPerVar());
		refPerVar.copyFrom(src1.getRefPerVar());
	}

	public void mergeWith(StateContainer src2) {
		intervalPerVar.mergeWith(src2.getIntervalPerVar());
		refPerVar.mergeWith(src2.getRefPerVar());
	}

	@Override
	public String toString() {
		return "Intervals{" + intervalPerVar.toString() + "} Refs{" + refPerVar.toString() + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intervalPerVar == null) ? 0 : intervalPerVar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateContainer other = (StateContainer) obj;
		if (intervalPerVar == null) {
			if (other.intervalPerVar != null)
				return false;
		} else if (!intervalPerVar.equals(other.intervalPerVar))
			return false;
		return true;
	}

	public AirCraftRefPerVar getRefPerVar() {
		return refPerVar;
	}
}
