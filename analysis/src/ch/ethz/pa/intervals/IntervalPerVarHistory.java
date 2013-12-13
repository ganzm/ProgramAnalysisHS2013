package ch.ethz.pa.intervals;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks history of all stores at all (relevant) program points. Per program point, the task of
 * tracking the evolution of a single store is delegated to {@link HistoryPerAnchor}.
 * 
 * @param <TAnchor>
 */
final public class IntervalPerVarHistory<TAnchor> {

	/**
	 * References to next-level delegates which handle history per program point.
	 */
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
	public void considerWidening(TAnchor anchor, IntervalPerVar store) {
		HistoryPerAnchor history = provideHistory(anchor);
		history.considerWidening(store);
	}

	/**
	 * Lookup the internal dictionary, fill in a new entry if needed.
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
