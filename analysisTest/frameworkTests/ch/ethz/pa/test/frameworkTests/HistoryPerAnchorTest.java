package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
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
	 * {@link HistoryPerAnchor#considerWidening(IntervalPerVar)} does not lead to widening
	 * when the interval is unchanged.
	 */
	@Test
	public void testNoWidening() {
		IntervalPerVar lastStore = null;
		for (int i = 0; i < 100; i++) {
			lastStore = storeWithInterval(1, 10);
			historyPerAnchor.considerWidening(lastStore);
		}
		Assert.assertEquals(new Interval(1, 10), lastStore.getIntervalForVar(INTERVAL_NAME));
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#considerWidening(IntervalPerVar)} does not lead to widening
	 * when the number of changes does not exceed the threshold
	 * {@link IntervalHistory#ITERATIONS_BEFORE_WIDENING}.
	 */
	@Test
	public void testNoWideningUpperBeforeIterationLimit() {
		IntervalPerVar lastStore = null;

		int upper = 10;
		for (int i = 0; i < IntervalHistory.ITERATIONS_BEFORE_WIDENING; i++) {
			lastStore = storeWithInterval(1, ++upper);
			historyPerAnchor.considerWidening(lastStore);
		}

		Assert.assertEquals(new Interval(1, upper), lastStore.getIntervalForVar(INTERVAL_NAME));
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#considerWidening(IntervalPerVar)} does not lead to widening
	 * when the number of changes does not exceed the threshold
	 * {@link IntervalHistory#ITERATIONS_BEFORE_WIDENING}.
	 */
	@Test
	public void testNoWideningLowerBeforeIterationLimit() {
		IntervalPerVar lastStore = null;

		int lower = 2;
		final int upper = 5;
		for (int i = 0; i < IntervalHistory.ITERATIONS_BEFORE_WIDENING; i++) {
			lastStore = storeWithInterval(--lower, upper);
			historyPerAnchor.considerWidening(lastStore);
		}

		Assert.assertEquals(new Interval(lower, upper), lastStore.getIntervalForVar(INTERVAL_NAME));
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#considerWidening(IntervalPerVar)} does lead to some widening
	 * when the interval is unchanged.
	 */
	@Test
	public void testWideningUpperOnIterationLimit() {
		IntervalPerVar lastStore = null;

		final int lower = 2;
		int upper = 10;
		for (int i = 0; i <= IntervalHistory.ITERATIONS_BEFORE_WIDENING; i++) {
			lastStore = storeWithInterval(lower, ++upper);
			historyPerAnchor.considerWidening(lastStore);
		}

		Interval resultingInterval = lastStore.getIntervalForVar(INTERVAL_NAME);
		Assert.assertFalse("widening expected", !resultingInterval.equals(new Interval(lower, upper)));
		Assert.assertTrue("widening incorrect", resultingInterval.covers(new Interval(lower, upper)));
		Assert.assertTrue("widening too imprecise", new Interval(lower, Integer.MAX_VALUE).covers(resultingInterval));
	}

	/**
	 * Test that repeated calling of
	 * {@link HistoryPerAnchor#considerWidening(IntervalPerVar)} does lead to some widening
	 * when the interval is unchanged.
	 */
	@Test
	public void testWideningLowerOnIterationLimit() {
		IntervalPerVar lastStore = null;

		int lower = 2;
		final int upper = 10;
		for (int i = 0; i <= IntervalHistory.ITERATIONS_BEFORE_WIDENING; i++) {
			lastStore = storeWithInterval(--lower, upper);
			historyPerAnchor.considerWidening(lastStore);
		}

		Interval resultingInterval = lastStore.getIntervalForVar(INTERVAL_NAME);
		Assert.assertFalse("widening expected", !resultingInterval.equals(new Interval(lower, upper)));
		Assert.assertTrue("widening incorrect", resultingInterval.covers(new Interval(lower, upper)));
		Assert.assertTrue("widening too imprecise", new Interval(Integer.MIN_VALUE, upper).covers(resultingInterval));
	}

}
