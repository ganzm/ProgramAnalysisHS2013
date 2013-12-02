package ch.ethz.pa.test.frameworkTests;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ch.ethz.pa.intervals.BitVariant;
import ch.ethz.pa.intervals.Interval;

public class IntervalBitVariantTests {

	@Test
	public void testVariantsForZero() {
		List<BitVariant> variants = new Interval(0).bitVariants();
		Assert.assertEquals(1, variants.size());
		Assert.assertEquals(-1, variants.get(0).mask);
		Assert.assertEquals(0, variants.get(0).bits);
	}

	@Test
	public void testVariantsForZeroToOne() {
		List<BitVariant> variants = new Interval(0, 1).bitVariants();
		Assert.assertEquals(1, variants.size());
		Assert.assertEquals(-1 ^ 1, variants.get(0).mask);
		Assert.assertEquals(0, variants.get(0).bits);
	}

	@Test
	public void testVariantsForZeroToTwo() {
		List<BitVariant> variants = new Interval(0, 2).bitVariants();
		Assert.assertEquals(2, variants.size());
	}

	@Test
	public void testVariantsForFourToFive() {
		List<BitVariant> variants = new Interval(4, 5).bitVariants();
		Assert.assertEquals(1, variants.size());
	}

	@Test
	public void testVariantsForThreeToSix() {
		List<BitVariant> variants = new Interval(3, 6).bitVariants();
		Assert.assertEquals(4, variants.size());
	}

}