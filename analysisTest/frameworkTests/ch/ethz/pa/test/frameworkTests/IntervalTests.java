package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.Interval;
import ch.ethz.pa.TriState;

public class IntervalTests {

	@Test
	public void testValueInRange() {
		Assert.assertTrue("lower bound should be covered", new Interval(10, 15).covers(10));
		Assert.assertTrue("upper bound should be covered", new Interval(10, 15).covers(15));
	}

	@Test
	public void testValueOutOfRange() {
		Assert.assertFalse("lower bound should be covered", new Interval(10, 15).covers(9));
		Assert.assertFalse("upper bound should be covered", new Interval(10, 15).covers(16));
	}

	@Test
	public void testRangeInRange() {
		Assert.assertTrue("subset should be covered", new Interval(10, 15).covers(new Interval(11, 14)));
		Assert.assertTrue("equal set should be covered", new Interval(10, 15).covers(new Interval(10, 15)));
	}

	@Test
	public void testRangeOutOfRange() {
		Assert.assertFalse("underrun not considered", new Interval(10, 15).covers(new Interval(9, 15)));
		Assert.assertFalse("overrun not considered", new Interval(10, 15).covers(new Interval(10, 16)));
	}

	@Test
	public void testMultiply() {
		Assert.assertEquals(new Interval(-9, 3), Interval.multiply(new Interval(-3, 1), new Interval(-1, 3)));
		Assert.assertEquals(new Interval(-4, 0), Interval.multiply(new Interval(-2, 0), new Interval(0, 2)));
		Assert.assertEquals(new Interval(0, 0), Interval.multiply(new Interval(-5, 5), new Interval(0, 0)));
	}

	@Test
	public void testDivide() {
		Assert.assertEquals(new Interval(-8, 4), Interval.divide(new Interval(-80, 40), new Interval(2, 10)));
		Assert.assertEquals(new Interval(-12, 4), Interval.divide(new Interval(-20, 60), new Interval(-5, -2)));
	}

	@Test
	public void testSubtract() {
		Assert.assertEquals(new Interval(-3, 4), Interval.subtract(new Interval(-1, 5), new Interval(1, 2)));
	}

	@Test
	public void testNegate() {
		Assert.assertEquals(new Interval(-3, 7), new Interval(-7, 3).negate());
	}

	@Test(expected = Exception.class)
	public void testInvalidIntervalThrowsException() {
		new Interval(5, 4);
	}

	@Test
	public void testGreaterEqual() {
		Assert.assertEquals(TriState.True, Interval.greaterEqual(new Interval(5), new Interval(5)));
		Assert.assertEquals(TriState.False, Interval.greaterEqual(new Interval(4), new Interval(5)));
		Assert.assertEquals(TriState.True, Interval.greaterEqual(new Interval(5, 7), new Interval(3, 5)));
		Assert.assertEquals(TriState.False, Interval.greaterEqual(new Interval(1, 2), new Interval(3, 5)));
		Assert.assertEquals(TriState.Unknown, Interval.greaterEqual(new Interval(1, 3), new Interval(3, 5)));
	}

	@Test
	public void testLimitToGreaterEqual() {
		Assert.assertEquals(new Interval(1), new Interval(1).limitToGreaterEqual(new Interval(1)));
		Assert.assertEquals(new Interval(3, 4), new Interval(3, 4).limitToGreaterEqual(new Interval(1, 2)));
		Assert.assertEquals(new Interval(2, 4), new Interval(2, 4).limitToGreaterEqual(new Interval(1, 3)));
		Assert.assertEquals(new Interval(2, 4), new Interval(1, 4).limitToGreaterEqual(new Interval(2, 3)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1, 2).limitToGreaterEqual(new Interval(3, 4)));
	}

	@Test
	public void testLimitToGreater() {
		Assert.assertEquals(new Interval(3, 4), new Interval(3, 4).limitToGreater(new Interval(0, 1)));
		Assert.assertEquals(new Interval(2, 4), new Interval(2, 4).limitToGreater(new Interval(0, 2)));
		Assert.assertEquals(new Interval(2, 4), new Interval(1, 4).limitToGreater(new Interval(1, 2)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1, 2).limitToGreater(new Interval(2, 3)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1).limitToGreater(new Interval(1)));
	}

	@Test
	public void testLimitToLowerEqual() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(3, 4).limitToLowerEqual(new Interval(1, 2)));
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 4).limitToLowerEqual(new Interval(1, 3)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 4).limitToLowerEqual(new Interval(2, 3)));
		Assert.assertEquals(new Interval(1, 2), new Interval(1, 2).limitToLowerEqual(new Interval(3, 4)));
		Assert.assertEquals(new Interval(1), new Interval(1).limitToLowerEqual(new Interval(1)));
	}

	@Test
	public void testLimitToLower() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(1).limitToLower(new Interval(1)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(3, 4).limitToLower(new Interval(2, 3)));
		Assert.assertEquals(new Interval(2, 3), new Interval(2, 4).limitToLower(new Interval(2, 4)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 4).limitToLower(new Interval(3, 4)));
		Assert.assertEquals(new Interval(1, 2), new Interval(1, 2).limitToLower(new Interval(4, 5)));
	}

	@Test
	public void testEmptyStaysEmpty() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToLower(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToLower(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToGreater(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToGreater(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToLowerEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToLowerEqual(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.limitToGreaterEqual(new Interval(5)));
		Assert.assertEquals(Interval.EMPTY_INTERVAL, new Interval(5).limitToGreaterEqual(Interval.EMPTY_INTERVAL));
	}
	
	@Test
	public void testJoin() {
		Assert.assertEquals(Interval.EMPTY_INTERVAL, Interval.EMPTY_INTERVAL.join(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(new Interval(1, 3), Interval.EMPTY_INTERVAL.join(new Interval(1, 3)));
		Assert.assertEquals(new Interval(1, 3), new Interval(1, 3).join(Interval.EMPTY_INTERVAL));
		Assert.assertEquals(new Interval(1, 5), new Interval(1, 2).join(new Interval(4, 5)));
	}

}
