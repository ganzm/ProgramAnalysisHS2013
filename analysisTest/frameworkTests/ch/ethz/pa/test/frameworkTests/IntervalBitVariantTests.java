package ch.ethz.pa.test.frameworkTests;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ch.ethz.pa.intervals.BitVariant;
import ch.ethz.pa.intervals.Interval;

public class IntervalBitVariantTests {

	@Test
	public void testNothing() {
		List<BitVariant> variants = new Interval(0).bitVariants();
		Assert.assertEquals(1, variants.size());
		Assert.assertEquals(-1, variants.get(0).mask);
		Assert.assertEquals(0, variants.get(0).bits);

	}
}
