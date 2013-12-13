package ch.ethz.pa.intervals;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ch.ethz.pa.intervals.IntervalPerVar.Pair;

/**
 * Tracks history of a store and all its intervals at one program point. Per interval, the task is
 * delegated to {@link IntervalHistory}.
 * 
 * @param <TAnchor>
 */
final public class HistoryPerAnchor {

	/**
	 * References to next-level delegates which handle history per interval.
	 */
	final private Map<String, IntervalHistory> intervalHistories;

	/**
	 * Reference to the previous store, so {@link IntervalPerVar#getDelta(IntervalPerVar)} can be
	 * used to check for changes between subsequent stores.
	 */
	private IntervalPerVar previousStore;

	public HistoryPerAnchor() {
		intervalHistories = new TreeMap<String, IntervalHistory>();
	}

	/**
	 * Check the new store for chances to apply widening. If widening is found to be in order, the
	 * store will be modified accordingly.
	 * <p/>
	 * 
	 * This method is supposed to be called in each iteration, with subsequent stores referring to
	 * the same program location. A reference to previous stores is kept for comparison.
	 * 
	 * @param store
	 *            store to analyze and manipulate
	 */
	public void considerWidening(IntervalPerVar store) {

		if (previousStore != null) {

			List<Pair> delta = store.getDelta(previousStore);

			for (Pair pair : delta) {
				IntervalHistory history = provideHistory(pair.name);
				if (history.recordAndConsiderWidening(pair.interval)) {
					store.putIntervalForVar(pair.name, history.getLatestInterval());
				}
			}
		}
		previousStore = store;
	}

	/**
	 * Lookup the internal dictionary, fill in a new entry if needed.
	 * 
	 * @param name
	 * @return
	 */
	private IntervalHistory provideHistory(String name) {
		IntervalHistory result = intervalHistories.get(name);
		if (result == null) {
			result = new IntervalHistory();
			intervalHistories.put(name, result);
		}
		return result;
	}
}
