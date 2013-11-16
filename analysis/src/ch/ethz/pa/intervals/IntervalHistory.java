package ch.ethz.pa.intervals;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class IntervalHistory {

	public final static int ITERATIONS_BEFORE_WIDENING = 5;

	private final Deque<Interval> history;

	public IntervalHistory() {
		super();
		history = new LinkedList<Interval>();
	}

	public boolean add(Interval interval) {
		if (recordAndCheckDifference(interval)) {
			if (considerAddingWidening()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param interval
	 * @return true if there was an older, different store
	 */
	private boolean recordAndCheckDifference(Interval interval) {

		if (history.size() == 0) {
			history.add(interval);
			return false;
		}

		if (history.getLast().equals(interval)) {
			return false;
		}

		history.add(interval);
		return true;
	}

	/**
	 * Core method. This looks at a sequence of intervals. If widening is considered, a wider
	 * interval will be added to the sequence.
	 * 
	 * @return true if widening was applied
	 */
	private boolean considerAddingWidening() {

		int pushLowerCount = 0;
		int pushUpperCount = 0;

		// iterate backwards, count how often bounds have been pushed
		Interval previous = null;
		Iterator<Interval> entry = history.descendingIterator();
		while (entry.hasNext()) {
			Interval nextInterval = entry.next();
			if (previous != null) {
				if (previous.goesLowerThan(nextInterval)) {
					++pushLowerCount;
				}
				if (previous.goesHigherThan(nextInterval)) {
					++pushUpperCount;
				}
			}
			previous = nextInterval;

			// no need to look further when both limits have been reached
			if (pushLowerCount > ITERATIONS_BEFORE_WIDENING && pushUpperCount > ITERATIONS_BEFORE_WIDENING)
				break;
		}

		Interval modifiedInterval = getLatestInterval();
		if (pushLowerCount > ITERATIONS_BEFORE_WIDENING) {
			modifiedInterval = modifiedInterval.join(new Interval(Integer.MIN_VALUE));
		}
		if (pushUpperCount > ITERATIONS_BEFORE_WIDENING) {
			modifiedInterval = modifiedInterval.join(new Interval(Integer.MAX_VALUE));
		}

		return recordAndCheckDifference(modifiedInterval);
	}

	public Interval getLatestInterval() {
		return history.getLast();
	}

}
