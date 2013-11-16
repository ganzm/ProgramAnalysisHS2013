package ch.ethz.pa.test.frameworkTests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ch.ethz.pa.intervals.HistoryPerAnchor;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalHistory;
import ch.ethz.pa.intervals.IntervalPerVar;

public class HistoryPerAnchorTest {

	private final static String INTERVAL_NAME = "A";

	private HistoryPerAnchor historyPerAnchor;

	private static IntervalPerVar storeWithInterval(int lower, int upper) {
		IntervalPerVar result = new IntervalPerVar();
		result.putIntervalForVar(INTERVAL_NAME, new Interval(lower, upper));
		return result;
	}

	@Before
	public void setup() {
		historyPerAnchor = new HistoryPerAnchor();
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#recordAndConsiderWidening(IntervalPerVar)} does not lead to widening
	 * when the interval is unchanged.
	 */
	@Test
	public void testNoWidening() {
		IntervalPerVar lastStore = null;
		for (int i = 0; i < 100; i++) {
			lastStore = storeWithInterval(1, 10);
			historyPerAnchor.recordAndConsiderWidening(lastStore);
		}
		Assert.assertEquals(new Interval(1, 10), lastStore.getIntervalForVar(INTERVAL_NAME));
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#recordAndConsiderWidening(IntervalPerVar)} does not lead to widening
	 * when the interval is unchanged.
	 */
	@Test
	public void testNoWideningUpperBeforeIterationLimit() {
		IntervalPerVar lastStore = null;

		int upper = 10;
		for (int i = 0; i < IntervalHistory.ITERATIONS_BEFORE_WIDENING; i++) {
			lastStore = storeWithInterval(1, ++upper);
			historyPerAnchor.recordAndConsiderWidening(lastStore);
		}

		Assert.assertEquals(new Interval(1, upper), lastStore.getIntervalForVar(INTERVAL_NAME));
	}

}
