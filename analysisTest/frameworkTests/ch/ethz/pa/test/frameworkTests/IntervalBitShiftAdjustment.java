package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.intervals.Interval;

public class IntervalBitShiftAdjustment {

	@Test
	public void testAdjustment() {
		Interval iRes = null;

		iRes = Interval.adjustIntervalForBitShift(new Interval(0, 31));
		Assert.assertEquals(new Interval(0, 31), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(0, 32));
		Assert.assertEquals(new Interval(0, 31), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(10, 20));
		Assert.assertEquals(new Interval(10, 20), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(-1, -1));
		Assert.assertEquals(new Interval(31, 31), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(-33, -31));
		Assert.assertEquals(new Interval(0, 31), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(32));
		Assert.assertEquals(new Interval(0), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(-32));
		Assert.assertEquals(new Interval(0), iRes);

		iRes = Interval.adjustIntervalForBitShift(new Interval(-64));
		Assert.assertEquals(new Interval(0), iRes);

	}
}
