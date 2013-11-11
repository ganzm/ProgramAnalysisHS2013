package ch.ethz.pa.test.frameworkTests;

import junit.framework.Assert;

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
		Assert.assertTrue("subset should be covered", new Interval(10, 15).covers(new Interval(11,14)));
		Assert.assertTrue("equal set should be covered", new Interval(10, 15).covers(new Interval(10,15)));
	}

	@Test
	public void testRangeOutOfRange() {
		Assert.assertFalse("underrun not considered", new Interval(10, 15).covers(new Interval(9,15)));
		Assert.assertFalse("overrun not considered", new Interval(10, 15).covers(new Interval(10,16)));
	}
	
	@Test
	public void testMultiply() {
		Assert.assertEquals(new Interval(-9,3), Interval.multiply(new Interval(-3,1), new Interval(-1,3)));
		Assert.assertEquals(new Interval(-4,0), Interval.multiply(new Interval(-2,0), new Interval(0,2)));
		Assert.assertEquals(new Interval(0,0), Interval.multiply(new Interval(-5,5), new Interval(0,0)));
	}

	@Test
	public void testDivide() {
		Assert.assertEquals(new Interval(-8,4), Interval.divide(new Interval(-80,40), new Interval(2,10)));
		Assert.assertEquals(new Interval(-12,4), Interval.divide(new Interval(-20,60), new Interval(-5,-2)));
	}
	

	@Test
	public void testSubtract() {
		Assert.assertEquals(new Interval(-3,4), Interval.subtract(new Interval(-1,5), new Interval(1,2)));
	}
	
	@Test
	public void testNegate() {
		Assert.assertEquals(new Interval(-3,7), new Interval(-7, 3).negate());
	}
	
	@Test(expected=Exception.class)
	public void testInvalidIntervalThrowsException()
	{
		new Interval(5,4);
	}
	
	@Test
	public void testGreaterEqual() {
		Assert.assertEquals(TriState.True, Interval.greaterEqual(new Interval(5), new Interval(5)));
		Assert.assertEquals(TriState.False, Interval.greaterEqual(new Interval(4), new Interval(5)));
		Assert.assertEquals(TriState.True, Interval.greaterEqual(new Interval(5,7), new Interval(3,5)));
		Assert.assertEquals(TriState.False, Interval.greaterEqual(new Interval(1,2), new Interval(3,5)));
		Assert.assertEquals(TriState.Unknown, Interval.greaterEqual(new Interval(1,3), new Interval(3,5)));		
	}
	
	@Test
	public void testLimitToGreaterEqual() {
		Assert.assertEquals(new Interval(2,4), new Interval(2,4).limitToGreaterEqual(new Interval(1,3)));
		Assert.assertEquals(new Interval(2,4), new Interval(1,4).limitToGreaterEqual(new Interval(2,3)));
	}

}
