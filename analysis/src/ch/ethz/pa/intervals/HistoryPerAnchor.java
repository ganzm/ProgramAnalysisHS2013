package ch.ethz.pa.intervals;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ch.ethz.pa.intervals.IntervalPerVar.Pair;

/**
 * Tracks history of a store and all its intervals for one program point. Per interval, the task is
 * delegated to {@link IntervalHistory}.
 * 
 * @param <TAnchor>
 */
public class HistoryPerAnchor {

	final private Deque<IntervalPerVar> storeHistories;
	final private Map<String, IntervalHistory> intervalHistories;

	public HistoryPerAnchor() {
		storeHistories = new LinkedList<IntervalPerVar>();
		intervalHistories = new TreeMap<String, IntervalHistory>();
	}

	public void recordAndConsiderWidening(IntervalPerVar intervalPerVar) {

		if (storeHistories.size() == 0) {
			storeHistories.addLast(intervalPerVar);
			return;
		}

		List<Pair> delta = intervalPerVar.getDelta(storeHistories.getLast());
		if (delta.size() > 0) {
			storeHistories.addLast(intervalPerVar);
		}

		for (Pair pair : delta) {
			IntervalHistory history = provideHistory(pair.name);
			if (history.add(pair.interval)) {
				storeHistories.getLast().putIntervalForVar(pair.name, history.getLatestInterval());
			}
		}
	}

	private IntervalHistory provideHistory(String name) {
		IntervalHistory result = intervalHistories.get(name);
		if (result == null) {
			result = new IntervalHistory();
			intervalHistories.put(name, result);
		}
		return result;
	}
}
