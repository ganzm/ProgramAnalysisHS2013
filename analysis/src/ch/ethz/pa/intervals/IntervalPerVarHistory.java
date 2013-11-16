package ch.ethz.pa.intervals;

import java.util.HashMap;
import java.util.Map;


public class IntervalPerVarHistory<TAnchor> {

	private Map<TAnchor, HistoryPerAnchor> histories;

	public IntervalPerVarHistory() {
		histories = new HashMap<TAnchor, HistoryPerAnchor>();
	}

	/**
	 * Add latest store to the history, and widen intervals where appropriate. This may modify the
	 * {@link store}.
	 * 
	 * @param anchor
	 *            denotes the program position the store belongs to
	 * @param store
	 *            the actual store to add and to widen
	 */
	public void recordAndConsiderWidening(TAnchor anchor, IntervalPerVar store) {
		HistoryPerAnchor history = provideHistory(anchor);
		history.recordAndConsiderWidening(store);
	}

	/**
	 * Lookup the chain of history for the given {@link anchor}. If there is none yet, create a new
	 * history chain.
	 * 
	 * @param anchor
	 * @return
	 */
	private HistoryPerAnchor provideHistory(TAnchor anchor) {
		HistoryPerAnchor result;
		result = histories.get(anchor);
		if (result == null) {
			result = new HistoryPerAnchor();
			histories.put(anchor, result);
		}
		return result;
	}

}
