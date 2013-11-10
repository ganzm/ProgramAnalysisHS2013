package ch.ethz.pa.test.frameworkTests;

import junit.framework.Assert;

import org.junit.Test;

import ch.ethz.pa.Interval;

public class IntervalTests {

	@Test
	public void testInRange() {
		Assert.assertTrue("lower bound should be covered", new Interval(10, 15).covers(10));
		Assert.assertTrue("upper bound should be covered", new Interval(10, 15).covers(15));
	}

	@Test
	public void testOutOfRange() {
		Assert.assertFalse("lower bound should be covered", new Interval(10, 15).covers(9));
		Assert.assertFalse("upper bound should be covered", new Interval(10, 15).covers(16));
	}
}
