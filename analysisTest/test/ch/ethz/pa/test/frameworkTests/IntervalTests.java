package ch.ethz.pa.test.frameworkTests;

import junit.framework.Assert;

import org.junit.Test;

import ch.ethz.pa.Interval;

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
		Assert.assertTrue("subset should be covered", new Interval(10, 15).covers(new Interval(11,14)));
		Assert.assertTrue("equal set should be covered", new Interval(10, 15).covers(new Interval(10,15)));
	}

	@Test
	public void testRangeOutOfRange() {
		Assert.assertFalse("underrun not considered", new Interval(10, 15).covers(new Interval(9,15)));
		Assert.assertFalse("overrun not considered", new Interval(10, 15).covers(new Interval(10,16)));
	}

}
