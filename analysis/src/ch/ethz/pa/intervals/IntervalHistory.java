package ch.ethz.pa.intervals;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Tracks the history of one {@link Interval}.
 */
public class IntervalHistory {

	public final static int ITERATIONS_BEFORE_WIDENING = 5;

	/**
	 * The actual evolution of the interval.
	 */
	private final Deque<Interval> history;

	public IntervalHistory() {
		super();
		history = new LinkedList<Interval>();
	}

	/**
	 * Receive the next state of the tracked {@link interval}. Returns true when widening was
	 * applied. In that case, the widened interval can be fetched by {@link #getLatestInterval()}.
	 * 
	 * @param interval
	 * @return true if widening was applied
	 */
	public boolean recordAndConsiderWidening(Interval interval) {
		if (recordAndCheckDifference(interval)) {
			if (considerAddingWidening()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Just record the new interval, if not already covered by the current last state.
	 * 
	 * @param interval
	 * @return true if there was an older, more narrow interval
	 */
	private boolean recordAndCheckDifference(Interval interval) {

		if (history.size() == 0) {
			history.add(interval);
			return false;
		}

		if (history.getLast().covers(interval)) {
			return false;
		}

		history.add(interval);
		return true;
	}

	/**
	 * Core widening method. This looks at a sequence of intervals. If widening is considered, a
	 * wider interval will be added to the sequence.
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

	/**
	 * Return the latest interval. If the state recorded before caused widening, this returns the
	 * widened interval.
	 * 
	 * @return
	 */
	public Interval getLatestInterval() {
		return history.getLast();
	}

}
