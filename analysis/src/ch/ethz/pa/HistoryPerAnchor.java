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

	private boolean considerAddingWidening(Deque<Interval> history) {
		// TODO Auto-generated method stub
		return false;
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
