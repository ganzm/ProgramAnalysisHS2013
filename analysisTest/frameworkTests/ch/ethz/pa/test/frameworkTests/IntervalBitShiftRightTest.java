package ch.ethz.pa.test.frameworkTests;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.logging.LoggerUtil;

public class IntervalBitShiftRightTest {

	private final Logger logger = Logger.getLogger(IntervalBitShiftRightTest.class.getSimpleName());

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	@Test
	public void testUnsignedShiftRight() {
		Interval iRes = null;

		Interval i1024 = new Interval(1024);
		Interval i512_1024 = new Interval(512, 1024);
		Interval i1 = new Interval(1);
		Interval i2 = new Interval(2);
		Interval i1_2 = new Interval(1, 2);

		// Default Case
		iRes = Interval.shiftRight(i1024, i1);
		Assert.assertEquals(new Interval(512), iRes);

		// Easy Case
		iRes = Interval.shiftRight(i1024, i2);
		Assert.assertEquals(new Interval(256), iRes);

		// Other Case
		iRes = Interval.shiftRight(i1024, i1_2);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftRight(i512_1024, i1);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftRight(i512_1024, i1_2);
		Assert.assertEquals(new Interval(128, 512), iRes);
	}

	@Test
	public void testNegativeUnsignedShiftRight() {
		Interval iRes = null;
		String expectedString = null;
		int expected1;
		int expected2;

		// no shift by 0
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "10000000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(0));
		Assert.assertEquals(new Interval(expected2), iRes);

		// simple shift by 1
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "11000000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(1));
		Assert.assertEquals(new Interval(expected2), iRes);

		// simple shift by 2
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "11100000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftRight(new Interval(expected1), new Interval(2));
		Assert.assertEquals(new Interval(expected2), iRes);

	}
}
