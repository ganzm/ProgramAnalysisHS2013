package ch.ethz.pa.test.frameworkTests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;
import ch.ethz.pa.intervals.IntervalPerVarHistory;

/**
 * Test the widening by {@link IntervalPerVarHistory}.
 */
public class IntervalPerVarHistoryTest {

	private final static String VARIABLE_A = "a";
	private final static String LABEL1 = "1stLabel";

	private IntervalPerVarHistory<Object> history;

	/**
	 * Helper creating a new store with one entry.
	 * 
	 * @param varName
	 * @param interval
	 * @return a new store
	 */
	private static IntervalPerVar newStoreWithEntry(String varName, Interval interval) {
		IntervalPerVar store1 = new IntervalPerVar();
		store1.putIntervalForVar(varName, interval);
		return store1;
	}

	/**
	 * Helper telling whether a variable evolves monotonically over two stores.
	 * 
	 * @param varName
	 * @param olderStore
	 * @param newerStore
	 * @return
	 */
	private static boolean stepIsMonotone(String varName, IntervalPerVar olderStore, IntervalPerVar newerStore) {
		return newerStore.getIntervalForVar(varName).covers(olderStore.getIntervalForVar(varName));
	}

	@Before
	public void setup() {
		history = new IntervalPerVarHistory<Object>();
	}

	/**
	 * Just add two stores.
	 */
	@Test
	public void testSimpleRecording() {
		IntervalPerVar store1 = newStoreWithEntry(VARIABLE_A, new Interval(4, 10));
		history.considerWidening(LABEL1, store1);

		IntervalPerVar store2 = newStoreWithEntry(VARIABLE_A, new Interval(4, 14));
		history.considerWidening(LABEL1, store2);

		Assert.assertTrue(stepIsMonotone(VARIABLE_A, store1, store2));
	}

	/**
	 * Record a number of stores with subsequently growing intervals. Widening may or may not
	 * happen.
	 */
	@Test
	public void testMonotonyWithManyRecords() {
		IntervalPerVar previousStore = null;
		for (int i = 0; i < 100; i++) {
			Interval nextInterval = new Interval(4, 10 + i);
			if (previousStore != null) {
				// since Soot will merge with previous state, we also can do that
				nextInterval = nextInterval.join(previousStore.getIntervalForVar(VARIABLE_A));
			}
			IntervalPerVar nextStore = newStoreWithEntry(VARIABLE_A, nextInterval);
			history.considerWidening(LABEL1, nextStore);
			if (previousStore != null) {
				Assert.assertTrue(stepIsMonotone(VARIABLE_A, previousStore, nextStore));
			}
			previousStore = nextStore;
		}
	}
}
