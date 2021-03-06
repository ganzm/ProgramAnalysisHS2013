package ch.ethz.pa.test.frameworkTests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.intervals.BitVariant;
import ch.ethz.pa.intervals.Interval;

public class IntervalBitVariantTests {

	@Test
	public void testEmptyVariantToInterval() {
		Assert.assertEquals(Interval.TOP_INTERVAL, new BitVariant(0, 0).toInterval());
	}

	@Test
	public void testPositiveVariantToInterval() {
		Assert.assertEquals(new Interval(4, 7), new BitVariant(-1 ^ 3, 4).toInterval());
	}

	@Test
	public void testNegativeVariantToInterval() {
		final int mask = -1 ^ 3;
		final int bits = -5 & mask;
		Assert.assertEquals(new Interval(-8, -5), new BitVariant(mask, bits).toInterval());
	}

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
		assertContains(variants, new BitVariant(-2, 0));
		assertContains(variants, new BitVariant(-1, 2));
	}

	private void assertContains(List<BitVariant> variants, BitVariant bitVariant) {
		for (BitVariant variant : variants) {
			if (variant.equals(bitVariant))
				return;
		}
		Assert.fail("expected " + bitVariant);
	}

	@Test
	public void testVariantsForFourToFive() {
		List<BitVariant> variants = new Interval(4, 5).bitVariants();
		Assert.assertEquals(1, variants.size());
		assertContains(variants, new BitVariant(-1 ^ 1, 4));
	}

	@Test
	public void testVariantsForThreeToSix() {
		List<BitVariant> variants = new Interval(3, 6).bitVariants();
		Assert.assertEquals(3, variants.size());
		assertContains(variants, new BitVariant(-1, 3));
		assertContains(variants, new BitVariant(-1 ^ 1, 4));
		assertContains(variants, new BitVariant(-1, 6));
	}

	@Test
	public void testVariantsForMinusTwoToOne() {
		List<BitVariant> variants = new Interval(-2, 1).bitVariants();
		Assert.assertEquals(2, variants.size());
		assertContains(variants, new BitVariant(-1 ^ 1, -2));
		assertContains(variants, new BitVariant(-1 ^ 1, 0));
	}

	@Test
	public void testVariantsFor500To999() {
		List<BitVariant> variants = new Interval(500, 999).bitVariants();
		Assert.assertEquals(7, variants.size());
		assertContains(variants, new BitVariant(-4, 500));
		assertContains(variants, new BitVariant(-8, 504));
		assertContains(variants, new BitVariant(-256, 512));
		assertContains(variants, new BitVariant(-128, 768));
		assertContains(variants, new BitVariant(-64, 896));
		assertContains(variants, new BitVariant(-32, 960));
		assertContains(variants, new BitVariant(-8, 992));
	}

}
