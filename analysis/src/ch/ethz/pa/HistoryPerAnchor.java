package ch.ethz.pa;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ch.ethz.pa.IntervalPerVar.Pair;

public class HistoryPerAnchor {

	final private Deque<IntervalPerVar> storeHistories;
	final private Map<String, Deque<Interval>> intervalHistories;

	public HistoryPerAnchor() {
		storeHistories = new LinkedList<IntervalPerVar>();
		intervalHistories = new TreeMap<String, Deque<Interval>>();
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
			Deque<Interval> history = provideHistory(pair.name);
			history.add(pair.interval);
			if (considerAddingWidening(history)) {
				storeHistories.getLast().putIntervalForVar(pair.name, history.getLast());
			}
		}
	}

	/**
	 * Core method. This looks at a sequence of intervals. If widening is considered, a wider
	 * interval will be added to the sequence.
	 * 
	 * @param history
	 * @return true if widening was applied
	 */
	private boolean considerAddingWidening(Deque<Interval> history) {
		// simple solution: widen after 5 iterations.
		if (history.size() < 4 || history.getLast().equals(Interval.TOP_INTERVAL)) {
			return false;
		}

		history.add(Interval.TOP_INTERVAL);
		return true;
	}

	private Deque<Interval> provideHistory(String name) {
		Deque<Interval> result = intervalHistories.get(name);
		if (result == null) {
			result = new LinkedList<Interval>();
			intervalHistories.put(name, result);
		}
		return result;
	}
}
